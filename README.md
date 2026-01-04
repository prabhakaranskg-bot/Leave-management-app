Leave Management System — Full Setup Guide
📌 Overview

This project is a Leave Management System built using:

Angular 17+

Spring Boot (Java 17+)

MySQL Database

It supports:

Employee management

Department management

Leave Types

Leave Requests (apply/approve/reject)

Holiday Calendar

Role-based security (planned with JWT)

✔️ Requirements

Java 17+

Angular 17+

Node.js & npm

MySQL

🗄️ Database Setup (MySQL)
Create database
CREATE DATABASE leave_mgmt;
USE leave_mgmt;

Department table
CREATE TABLE department (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

Employee table
CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(20),
    dept_id INT,
    join_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_emp_dept
        FOREIGN KEY (dept_id)
        REFERENCES department(dept_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

Indexes
CREATE INDEX idx_employee_dept ON employee(dept_id);
CREATE INDEX idx_employee_email ON employee(email);

Leave Type
CREATE TABLE leave_type (
    leave_type_id INT AUTO_INCREMENT PRIMARY KEY,
    leave_code VARCHAR(10) NOT NULL UNIQUE,
    leave_name VARCHAR(50) NOT NULL,
    annual_allocation INT NOT NULL,
    carry_forward TINYINT(1) DEFAULT 0
) ENGINE=InnoDB;

Leave Request
CREATE TABLE leave_request (
    leave_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason TEXT,
    status VARCHAR(20) DEFAULT 'PENDING',
    applied_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approved_by INT NULL,
    approved_on TIMESTAMP NULL,
    CONSTRAINT fk_leave_emp FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE,
    CONSTRAINT fk_leave_type FOREIGN KEY (leave_type_id) REFERENCES leave_type(leave_type_id),
    CONSTRAINT fk_approved_by FOREIGN KEY (approved_by) REFERENCES employee(emp_id),
    CONSTRAINT chk_dates CHECK (end_date >= start_date)
) ENGINE=InnoDB;


MySQL additional constraint (if required):

ALTER TABLE leave_request
ADD CONSTRAINT chk_dates_valid CHECK (end_date >= start_date);

Indexes
CREATE INDEX idx_leave_emp ON leave_request(emp_id);
CREATE INDEX idx_leave_status ON leave_request(status);

Holiday Calendar
CREATE TABLE holiday_calendar (
    holiday_id INT AUTO_INCREMENT PRIMARY KEY,
    holiday_date DATE NOT NULL UNIQUE,
    description VARCHAR(200) NOT NULL
) ENGINE=InnoDB;

🔧 Spring Boot (Back End) Setup
Dependencies

Spring Web

Spring Data JPA

MySQL Driver

Lombok

Spring Security (optional for JWT)

Create modules

Models

Repositories

Services

Controllers

Entities:

Department

Employee

LeaveType

LeaveRequest

HolidayCalendar

User (for login/security)

🔑 Backend Configuration Example

Never hardcode secrets

Use aliases instead:

spring.datasource.url=jdbc:mysql://localhost:3306/leave_mgmt
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}


Set them as environment variables.

💻 Angular (Front End) Setup
ng new leave-management-app
cd leave-management-app
ng serve


Add Angular Material:

ng add @angular/material

Environment configuration

src/environments/environment.ts

export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};


Create modules & components:

ng g c department --standalone
ng g c employee --standalone
ng g c leave-type --standalone
ng g c leave-request --standalone
ng g c holiday --standalone

ng g s services/department
ng g s services/employee
ng g s services/leave-type
ng g s services/leave-request
ng g s services/holiday


CRUD completed for:

Department

Employee

LeaveType

LeaveRequest

Holiday Calendar

🔒 Planned Next Features

JWT Authentication

Role-based access:

Admin

Manager

Employee

Separate approver login

Audit logs

🚀 Upload to GitHub
git init
git add .
git commit -m "Initial Commit"
git branch -M main
git remote add origin <YOUR_REPOSITORY_URL>
git push -u origin main

🔐 Never commit real secrets

Use placeholders like:

<YOUR_GITHUB_PAT>
<YOUR_DB_USERNAME>
<YOUR_DB_PASSWORD>
<YOUR_JWT_SECRET>

🛑 Secret Alias List Used

DB_USERNAME

DB_PASSWORD

DB_URL

JWT_SECRET

GITHUB_PAT

API_KEY