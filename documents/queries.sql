Database Create Queries :

create table login_credential(login_id bigint PRIMARY KEY, user_name varchar(25) unique, password varchar(25) NOT NULL, is_employee boolean not null);
create table citizen_address(address_id bigint primary key, street varchar(30) not null, city varchar(25) not null, state varchar(25) not null, pincode int not null);
create table citizen_master(citizen_details_id bigint PRIMARY KEY, first_name varchar(50) not null, middle_name varchar(50), last_name varchar(50),date_of_birth date not null,gender varchar(25) not null, email varchar(50) not null, phone bigint not null, address_ref bigint, income_group_ref bigint not null, profession_ref bigint  not null, aadhar_number bigint not null unique, pancard_number varchar(50), citizen_ref bigint, FOREIGN KEY(citizen_ref) REFERENCES login_credential(login_id),FOREIGN KEY(address_ref ) REFERENCES citizen_address(address_id),FOREIGN KEY(profession_ref) REFERENCES profession(profession_id),FOREIGN KEY(income_group_ref ) REFERENCES income_group(income_group_id));
create table bank(bank_id bigint primary key, bank_name varchar(100) not null unique);
create table sector(sector_id bigint primary key,sector_name varchar(100) unique not null);
create table profession(profession_id bigint primary key,profession_name varchar(100) unique not null);
create table income_group(income_group_id bigint primary key, income_group_name varchar(50) unique not null);
create table ministry(ministry_id bigint primary key,ministry_name varchar(100) not null unique);
create table scheme_eligibility(scheme_eligibility_id bigint primary key, min_age int not null,max_age int not null, income_group_ref bigint, gender varchar(25) not null, profession_ref bigint,FOREIGN KEY(income_group_ref) REFERENCES income_group(income_group_id),FOREIGN KEY(profession_ref) REFERENCES profession(profession_id));
create table scheme_master(scheme_id bigint primary key, scheme_name varchar(100) not null unique,summary varchar(500) not null,description varchar(2000) not null, image_path varchar(250) not null,ministry_ref bigint,sector_ref bigint, start_date date not null, scheme_eligibility_ref bigint,status boolean not null,FOREIGN KEY(ministry_ref) REFERENCES ministry(ministry_id), FOREIGN KEY(sector_ref) REFERENCES sector(sector_id),FOREIGN KEY(scheme_eligibility_ref) REFERENCES scheme_eligibility(scheme_eligibility_id));
create table scheme_banks(scheme_bank_id bigint primary key,scheme_ref bigint,bank_ref bigint,FOREIGN KEY(scheme_ref) REFERENCES scheme_master(scheme_id),FOREIGN KEY(bank_ref) REFERENCES bank(bank_id) );
create table document(document_id bigint primary key, document_name varchar(100)not null);
create table scheme_documents(scheme_documents_id bigint primary key, scheme_ref bigint, document_ref bigint,FOREIGN KEY(scheme_ref) REFERENCES scheme_master(scheme_id),FOREIGN KEY(document_ref ) REFERENCES document(document_id));
create table scheme_applicant(scheme_applicant_id bigint primary key,scheme_ref bigint, login_ref bigint,bank_ref  bigint,account_number bigint not null, type_of_account varchar(100) not null,ifsc varchar(11) not null,branch varchar(100) not null, approved_status boolean ,reason varchar(500),FOREIGN KEY(scheme_ref) REFERENCES scheme_master(scheme_id),FOREIGN KEY(login_ref) REFERENCES login_credential(login_id), FOREIGN KEY(bank_ref) REFERENCES bank(bank_id));
create table scheme_applicant_documents(scheme_applicant_documents_id bigint primary key, scheme_applicant_ref bigint, document_ref bigint, document_path varchar(250) not null,FOREIGN KEY(scheme_applicant_ref ) REFERENCES scheme_applicant(scheme_applicant_id),FOREIGN KEY(document_ref ) REFERENCES document(document_id));
=======================================================
Database Insert Queries :

BANK:
insert into bank values(1,'HSBC');
insert into bank values(2,'SBI');
insert into bank values(3,'ICICI');
insert into bank values(4,'Indian Bank');
insert into bank values(5,'PNB');

DOCUMENT:
insert into document values(1,'PAN');
insert into document values(2,'Aadhar');
insert into document values(3,'Passport');
insert into document values(4,'Birth Certificate');
insert into document values(5,'Deat Certificate');
insert into document values(6,'Caste Certificate');
insert into document values(7,'Merit Certificate');

INCOME_GROUP:
insert into income_group values(1,'BPL');
insert into income_group values(2,'Lower');
insert into income_group values(3,'Middle');
insert into income_group values(4,'Upper');

LOGIN_CREDENTIAL: (for gov employee)
insert into login_credential values(1,'emp1','123',true);
insert into login_credential values(2,'emp2','123',true);
insert into login_credential values(3,'emp3','123',true);

MINISTRY:
insert into ministry values(1,'Finance');
insert into ministry values(2,'Education');
insert into ministry values(3,'Home Affairs');
insert into ministry values(4,'Rural Development');
insert into ministry values(5,'Tribal Affairs');
insert into ministry values(6,'Agriculture');

PROFESSION:
insert into profession values(1,'Farmer');
insert into profession values(2,'Government Employee');
insert into profession values(3,'Public');
insert into profession values(4,'Private');
insert into profession values(5,'Student');

SECTOR:
insert into sector values(1,'Pension');
insert into sector values(2,'PPF');
insert into sector values(3,'Mediclaim');
===============================
Database Sequence Queries :

create sequence scheme_seq as bigint start with 1;
create sequence citizen_seq as bigint start with 1;