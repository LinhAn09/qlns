use master
go
drop database masv_hoten_java5
go
create database masv_hoten_java5
go
use masv_hoten_java5
go




create table [Users]
(
	Username varchar(50) primary key,
	[Password] varchar(50)
)
go

create table Categorys(
	CategoryID int primary key,
	Name varchar(50)
)
go

create table Products(
	ProductID int identity(1,1) primary key,
	Name nvarchar(50),
	Price int,
	Amount int,
	ProImg varchar(50),
	CategoryID int references Categorys(CategoryID)
)
go

create table Customs(
	CustomID int identity(1,1) primary key,
	Name varchar(50),
	Phone varchar(10)
)
go

create table Orders(
	OrderID int identity(1,1) primary key,
	ODate date,
	CustomID int references Customs(CustomID)
)
go


create table Dentails(
	OrderID int references Orders(OrderID),
	ProductID int references Products(ProductID),
	Price int,
	Amount int
	Constraint pk_CTDH primary key(OrderID,ProductID)
)
go

--2. thêm dữ liệu vào
--Bước 1 thêm bảng User, Depart
--Bước 2 Staff
--Bước 3 Record

insert into [Users] values('admin','123')
go
insert into [Users] values('phuc','123')
go
insert into [Users] values('thanh','123')
go
insert into [Users] values('loc','123')
go
insert into [Users] values('cuong','123')
go
insert into [Users] values('vu','123')
go


insert into Categorys values (1, 'Hoa')
go

insert into Categorys values(2, 'Trái cây')
go

insert into Products values( N'Hoa Nhài',100000,5,'nhai.jpg',1)
go

insert into Products values( N'Sầu Riêng',100000,5,'sau.jpg',2)


insert into Products values( N'Hoa Lan',100000,3,'lan.jpg',1)
go

insert into Products values( N'Hoa Hồng',100000,5,'hong.jpg',1)
go

insert into Products values( N'Cam',100000,5,'cam.jpg',2)
go

insert into Products values( N'Hoa Lavender',100000,5,'lavender.jpg',1)
go

insert into Products values( N'Xoài',100000,7,'xoai.jpg',2)
go

insert into Products values( N'Hoa Sen',100000,9,'sen.jpg',1)
go

insert into Products values( N'Bơ',100000,5,'bo.jpg',2)
go

insert into Products values( N'Hoa Cẩm Tú Cầu',100000,5,'cam_tu_cau.jpg',1)
go

insert into Customs values ('Trang' , '0909090909')
go

insert into Customs values('Anh', '0808080808')
go

insert into Orders values ('2020-10-01', 1)
go

insert into Orders values ('2020-01-10', 2)
go


insert into Dentails values (1,1, 100000,2)
go

insert into Dentails values (1,2, 100000,1)
go


