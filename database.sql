USE [master]
GO
/****** Object:  Database [ElectronicsShopSpringMVC]    Script Date: 2022-06-08 9:49:49 PM ******/
CREATE DATABASE [ElectronicsShopSpringMVC]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ElectronicsShopSpringMVC', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ElectronicsShopSpringMVC.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ElectronicsShopSpringMVC_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ElectronicsShopSpringMVC_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ElectronicsShopSpringMVC].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET ARITHABORT OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET RECOVERY FULL 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET  MULTI_USER 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'ElectronicsShopSpringMVC', N'ON'
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET QUERY_STORE = OFF
GO
USE [ElectronicsShopSpringMVC]
GO
/****** Object:  Table [dbo].[cart]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart_product]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_product](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[cart_id] [bigint] NULL,
	[product_id] [bigint] NULL,
	[quantity] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[avatar] [varchar](255) NULL,
	[code] [varchar](255) NULL,
	[name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[comment]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comment](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[content] [nvarchar](max) NULL,
	[product_id] [bigint] NULL,
	[user_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[quantity] [bigint] NULL,
	[order_id] [bigint] NULL,
	[product_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[email] [varchar](255) NULL,
	[fullname] [nvarchar](255) NULL,
	[note] [nvarchar](max) NULL,
	[phone] [varchar](255) NULL,
	[status] [int] NULL,
	[user_id] [bigint] NULL,
	[price_total] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[avatar] [varchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[name] [nvarchar](255) NULL,
	[price] [float] NULL,
	[category_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[code] [varchar](255) NULL,
	[name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_role]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_role](
	[user_id] [bigint] NOT NULL,
	[role_id] [bigint] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[createdAt] [datetime] NULL,
	[createdBy] [nvarchar](255) NULL,
	[updatedAt] [datetime] NULL,
	[updatedBy] [nvarchar](255) NULL,
	[address] [nvarchar](255) NULL,
	[avatar] [varchar](255) NULL,
	[birthday] [datetime] NULL,
	[email] [varchar](255) NULL,
	[facebook] [varchar](255) NULL,
	[fullname] [nvarchar](255) NULL,
	[gender] [nvarchar](5) NULL,
	[jobs] [nvarchar](255) NULL,
	[password] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[status] [int] NULL,
	[username] [varchar](255) NULL,
	[cart_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[cart] ON 

INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (1, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (2, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (3, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (4, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (5, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (6, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (7, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (8, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (9, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (10, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (11, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (12, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (13, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (14, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (15, CAST(N'2022-01-13T10:13:29.550' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[cart] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy]) VALUES (16, CAST(N'2022-01-13T10:15:58.507' AS DateTime), NULL, CAST(N'2022-01-13T10:15:58.507' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[cart] OFF
GO
SET IDENTITY_INSERT [dbo].[category] ON 

INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (1, NULL, NULL, CAST(N'2021-12-26T11:22:56.557' AS DateTime), N'admin', NULL, N'dien-thoai', N'Điện thoại')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (2, NULL, NULL, NULL, NULL, NULL, N'laptop', N'Laptop')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (3, NULL, NULL, NULL, NULL, NULL, N'tablet', N'Tablet')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (4, NULL, NULL, NULL, NULL, NULL, N'dong-ho', N'Đồng hồ')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (5, NULL, NULL, NULL, NULL, NULL, N'man-hinh', N'Màn hình')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (6, NULL, NULL, NULL, NULL, NULL, N'sim-the', N'Sim thẻ')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (7, NULL, NULL, NULL, NULL, NULL, N'phu-kien', N'Phụ kiện')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (8, CAST(N'2021-12-26T11:02:39.300' AS DateTime), N'admin', CAST(N'2021-12-26T11:06:16.830' AS DateTime), N'admin', N'1640491359227_samsung-galaxy-watch-3-41mm-vang.png', N'nha-thong-minh', N'Nhà thông minh')
INSERT [dbo].[category] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [code], [name]) VALUES (9, CAST(N'2021-12-26T11:14:45.723' AS DateTime), N'admin', CAST(N'2021-12-26T11:14:45.723' AS DateTime), N'admin', NULL, N'hang-cu', N'Hàng cũ')
SET IDENTITY_INSERT [dbo].[category] OFF
GO
SET IDENTITY_INSERT [dbo].[comment] ON 

INSERT [dbo].[comment] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [content], [product_id], [user_id]) VALUES (1, NULL, NULL, NULL, NULL, N'Sản phẩm tốt', 1, 40)
INSERT [dbo].[comment] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [content], [product_id], [user_id]) VALUES (2, NULL, NULL, NULL, NULL, N'Giá hợp lý', 1, 1)
SET IDENTITY_INSERT [dbo].[comment] OFF
GO
SET IDENTITY_INSERT [dbo].[order_detail] ON 

INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (10, NULL, NULL, NULL, NULL, 1, 4, 23)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (11, NULL, NULL, NULL, NULL, 1, 5, 23)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (12, NULL, NULL, NULL, NULL, 1, 5, 15)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (13, NULL, NULL, NULL, NULL, 1, 6, 2)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (14, NULL, NULL, NULL, NULL, 1, 7, 16)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (15, NULL, NULL, NULL, NULL, 2, 9, 16)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (16, NULL, NULL, NULL, NULL, 1, 9, 17)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (17, NULL, NULL, NULL, NULL, 1, 13, 16)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (18, NULL, NULL, NULL, NULL, 1, 13, 17)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (19, NULL, NULL, NULL, NULL, 1, 13, 3)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (20, NULL, NULL, NULL, NULL, 1, 15, 2)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (21, NULL, NULL, NULL, NULL, 5, 15, 15)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (26, NULL, NULL, NULL, NULL, 1, 18, 2)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (27, NULL, NULL, NULL, NULL, 2, 20, 21)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (28, NULL, NULL, NULL, NULL, 1, 22, 18)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (29, NULL, NULL, NULL, NULL, 1, 23, 18)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (30, NULL, NULL, NULL, NULL, 2, 24, 2)
INSERT [dbo].[order_detail] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [quantity], [order_id], [product_id]) VALUES (31, NULL, NULL, NULL, NULL, 1, 24, 3)
SET IDENTITY_INSERT [dbo].[order_detail] OFF
GO
SET IDENTITY_INSERT [dbo].[orders] ON 

INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (4, CAST(N'2022-01-14T09:59:06.897' AS DateTime), NULL, CAST(N'2022-01-14T17:45:03.840' AS DateTime), N'anonymousUser', N'Hoàng Mai, Hà Nội', N'user10@gmail.com', N'Việt Tú', N'', N'0966871026', 4, 40, 22500000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (5, CAST(N'2022-01-14T10:07:52.180' AS DateTime), NULL, CAST(N'2022-01-14T17:32:47.560' AS DateTime), N'admin', N'Hà Nam', N'user10@gmail.com', N'User 10 update', N'', N'0852369741', 2, 40, 45500000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (6, CAST(N'2022-01-14T10:14:57.327' AS DateTime), NULL, CAST(N'2022-01-14T17:29:52.063' AS DateTime), N'anonymousUser', N'Hoàng Mai, Hà Nội', N'user10@gmail.com', N'Việt Tú', N'', N'0966871026', 4, 40, 41990000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (7, CAST(N'2022-01-14T10:17:59.703' AS DateTime), NULL, CAST(N'2022-01-14T17:26:11.717' AS DateTime), N'admin', N'abcxyz', N'user10@gmail.com', N'User 10 update', N'', N'0852369741', 2, 40, 16990000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (9, CAST(N'2022-01-14T14:12:59.890' AS DateTime), NULL, CAST(N'2022-01-14T14:12:59.890' AS DateTime), NULL, N'tokyo', N'user11@gmail.com', N'user11', N'', N'0088552211', 0, 42, 59680000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (13, CAST(N'2022-01-14T14:17:46.217' AS DateTime), NULL, CAST(N'2022-01-14T14:17:46.217' AS DateTime), NULL, N'canada', N'user11@gmail.com', N'user11', N'', N'0088552211', 0, 42, 50680000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (15, CAST(N'2022-01-14T17:48:17.907' AS DateTime), NULL, CAST(N'2022-01-15T11:45:48.187' AS DateTime), N'anonymousUser', N'Hà nội', N'tupham14102001@gmail.com', N'User 10 update 2', N'', N'0852369741', 4, 40, 156990000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (18, CAST(N'2022-01-14T18:02:26.110' AS DateTime), NULL, CAST(N'2022-01-15T14:19:54.317' AS DateTime), N'anonymousUser', N'thái bình', N'tupham14102001@gmail.com', N'User 10 update 2', N'', N'0852369741', 4, 40, 41990000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (20, CAST(N'2022-01-14T18:06:20.310' AS DateTime), NULL, CAST(N'2022-01-14T18:06:20.310' AS DateTime), NULL, N'a', N'tupham14102001@gmail.com', N'User 10 update 2', N'', N'0852369741', 0, 40, 7980000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (22, CAST(N'2022-01-14T18:17:57.750' AS DateTime), NULL, CAST(N'2022-01-14T18:17:57.750' AS DateTime), NULL, N'abc', N'tupham14102001@gmail.com', N'User 10 abc', N'', N'0852369741', 0, 40, 20690000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (23, CAST(N'2022-01-14T18:21:17.500' AS DateTime), NULL, CAST(N'2022-01-14T18:21:17.500' AS DateTime), NULL, N'hh', N'tupham14102001@gmail.com', N'User 10 hhhhh', N'', N'0852369741', 0, 40, 20690000)
INSERT [dbo].[orders] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [email], [fullname], [note], [phone], [status], [user_id], [price_total]) VALUES (24, CAST(N'2022-01-15T14:15:02.610' AS DateTime), NULL, CAST(N'2022-01-15T14:15:02.610' AS DateTime), NULL, N'Hà Nội ', N'tupham14102001@gmail.com', N'User 10 ', N'', N'0852369741', 0, 40, 91970000)
SET IDENTITY_INSERT [dbo].[orders] OFF
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (1, NULL, NULL, CAST(N'2022-01-12T10:46:52.680' AS DateTime), N'admin', N'1641444227574_ip_11.png', N'<h2><strong>Đ&aacute;nh gi&aacute; iPhone 11 - Camera n&acirc;ng cấp, m&agrave;u sắc trẻ trung</strong></h2>

<p>Apple lu&ocirc;n l&agrave;m h&agrave;i l&ograve;ng t&iacute;n đồ iFan với c&aacute;c d&ograve;ng iPhone trong từng ph&acirc;n kh&uacute;c gi&aacute;. Đặc biệt, phi&ecirc;n bản&nbsp;<strong>iPhone 11</strong>&nbsp;vừa ra mắt nhưng đ&atilde; chiếm lĩnh được thị trường smartphone tr&ecirc;n to&agrave;n thế giới với gi&aacute; cả phải chăng. C&ugrave;ng t&igrave;m hiểu chi tiết r&otilde; hơn qua b&agrave;i viết sau đ&acirc;y để c&oacute; quyết định c&oacute; n&ecirc;n mua hay kh&ocirc;ng nh&eacute;!</p>

<h3><strong>Thiết kế sang trọng, kế thừa iPhone XR</strong></h3>

<p>Cụm camera ph&iacute;a sau đ&atilde; được sắp xếp lại giống với Huawei Mate 20 Pro. M&agrave;n h&igrave;nh điện thoại iPhone 11 sở hữu notch rộng d&agrave;i như c&aacute;c phi&ecirc;n bản tiền nhiệm v&agrave; giữ nguy&ecirc;n thiết kế như iPhone XR. Notch t&iacute;ch hợp Face ID, loa &acirc;m thanh v&agrave; camera selfie.</p>

<p>Tấm nền IPS LCD kh&ocirc;ng c&oacute; cải tiến về độ s&aacute;ng v&agrave; tương phản. Tấm nền được bao bọc bởi lớp k&iacute;nh Gorilla Glass cao cấp, cạnh viền c&oacute; g&oacute;c độ cong kh&aacute; nhỏ. Điện thoại trọng lượng nhẹ, dễ d&agrave;ng cầm nắm chỉ với 194 gram. K&iacute;ch thước chi tiết lần lượt l&agrave; 5.94H x 2.98W x 0.33 D (inch).</p>

<p><img alt="Đánh giá thiết kế iPhone 11" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-3.jpg" style="height:100%" /></p>

<h3><strong>M&agrave;n h&igrave;nh 6.1 inch, c&ocirc;ng nghệ m&agrave;n h&igrave;nh HD Retina lỏng</strong></h3>

<p>Apple ứng dụng c&ocirc;ng nghệ m&agrave;n h&igrave;nh HD Retina lỏng c&ugrave;ng m&agrave;n h&igrave;nh cảm ứng đa điểm LCD k&iacute;ch thước to&agrave;n m&agrave;n h&igrave;nh l&agrave; 6.1 inch với 326 ppi cho iPhone 11. M&agrave;n h&igrave;nh c&oacute; độ ph&acirc;n giải thực tế l&agrave; 828x1792 pixel vừa đủ d&ugrave;ng. B&ecirc;n cạnh đ&oacute;, c&ocirc;ng nghệ True Wireless được t&iacute;ch hợp trong khi chuẩn tỉ lệ tương phản video HDR chỉ được hiển thị tr&ecirc;n m&agrave;n h&igrave;nh Oled.</p>

<p>Độ trễ m&agrave;n h&igrave;nh gần như bằng 0, tốc độ l&agrave;m mới m&agrave;n h&igrave;nh nằm ở mức 60 Hz. Độ ch&iacute;nh x&aacute;c m&agrave;u đạt đến mức tối đa, điểm DeltaE trung b&igrave;nh đạt 1.1, độ lệch tối đa 2.4 RGB. Ngay cả khi độ s&aacute;ng giảm xuống mức 2.3 nit độ ch&iacute;nh x&aacute;c n&agrave;y vẫn được giữ nguy&ecirc;n.</p>

<p><img alt="Đánh giá màn hình iPhone 11" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-11.jpg" style="height:100%; width:100%" /></p>

<h3><strong>Chip</strong><strong>set A13 mạnh mẽ, hiệu năng vượt trội</strong></h3>

<p>iPhone 11 sử dụng Chipset Apple A13 Bionic c&ugrave;ng Ram 4GB. Con chip n&agrave;y được đ&aacute;nh gi&aacute; l&agrave; tiết kiệm điện v&agrave; chạy nhanh hơn so với thế hệ A12. Với bộ xử l&yacute; 6 nh&acirc;n 2x2.65 GHz Lightning v&agrave; CPU thunder 4x1.8 GHz. Neural Engine thế hệ 3 c&oacute; 8 nh&acirc;n phụ tr&aacute;ch t&iacute;nh năng xử l&yacute; h&igrave;nh ảnh như Night Mode v&agrave; Deep Fusion.<br />
H3: Hệ điều h&agrave;nh</p>

<p>Hệ điều h&agrave;nh iOS 13 được sử dụng cho iPhone 11, nếu muốn c&agrave;i đặt l&ecirc;n c&aacute;c phi&ecirc;n bản cao hơn, bạn cần phải update hệ thống. T&iacute;nh năng FaceID được ứng dụng gia tăng độ bảo mật của điện thoại. T&iacute;nh năng 3D Touch đ&atilde; được thay thế bằng Haptic Touch. Những phi&ecirc;n bản về sau c&oacute; thể sẽ ứng dụng Haptic Touch v&agrave;o.</p>

<p><img alt="Đánh giá cấu hình iPhone 11" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-13.jpg" style="height:100%; width:100%" /></p>

<h3><strong>Camera sau ấn tượng, c&ocirc;ng nghệ chống rung quang học</strong></h3>

<p>iPhone 11 c&oacute; tổng cộng 2 camera sau: 2 camera sau v&agrave; 1 đ&egrave;n flash. Camera ch&iacute;nh c&oacute; độ ph&acirc;n giải 12 MP được t&iacute;ch hợp c&ocirc;ng nghệ chống rung quang học OIS, 1 camera g&oacute;c rộng 12 MP.</p>

<p>Camera ch&iacute;nh với ti&ecirc;u cự 28mm, khẩu độ l&agrave; f/1.8, c&oacute; Dual Pixel AF, chống rung quang học v&agrave; chế độ Night Mode c&oacute; sẵn tr&ecirc;n camera. Camera g&oacute;c rộng 13mm kh&ocirc;ng c&oacute; khả năng lấy n&eacute;t, kh&ocirc;ng c&oacute; chống rung quang học.</p>

<p><img alt="Đánh giá camera sau iPhone 11" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-2.jpg" style="height:100%; width:100%" /></p>

<p>Chất lượng ảnh độ noise thấp, chi tiết sắc n&eacute;t với dải m&agrave;u động cực kỳ ấn tượng. V&igrave; m&agrave;u sắc ch&acirc;n thực, r&otilde; n&eacute;t n&ecirc;n kh&ocirc;ng tr&aacute;nh khỏi t&igrave;nh trạng nhạt nh&ograve;a, thiếu sức sống. Camera g&oacute;c rộng hỗ trợ gi&uacute;p ảnh trở n&ecirc;n chi tiết, m&agrave;u sắc, độ tương phản v&agrave; giải m&agrave;u động th&igrave; tương phản với camera ch&iacute;nh.</p>

<p>Khi chụp trong m&ocirc;i trường thiếu s&aacute;ng, ban đ&ecirc;m th&igrave; chế độ chụp đ&ecirc;m Night Mode l&agrave; cứu tinh trong l&uacute;c n&agrave;y. Chất lượng ảnh nhiều chi tiết, r&otilde; n&eacute;t, đủ thấy r&otilde;, ấn tương l&agrave; kh&ocirc;ng bị mờ nh&ograve;e. Trong trường hợp chụp ch&acirc;n dung, c&aacute;c hiệu ứng &aacute;nh s&aacute;ng kh&aacute;c nhau mở rộng cho bạn sự lựa chọn. Với chip xử l&yacute; 13, việc t&aacute;ch chủ thể v&agrave; ph&ocirc;ng nền trở n&ecirc;n đơn giản hơn bao giờ hết.</p>

<h3><strong>Camera selfie</strong><strong>&nbsp;ch&acirc;n thực với dải m&agrave;u rộng</strong></h3>

<p>Camera selfie iPhone 11 c&oacute; độ ph&acirc;n giải l&agrave; 12MP, ti&ecirc;u cự 22mm, f/2.2. T&iacute;nh năng smart HDR gi&uacute;p ảnh chi tiết, ch&acirc;n thật, sắc n&eacute;t hơn rất nhiều với dải m&agrave;u rộng. Với nhu cầu &ldquo;tự sướng&rdquo; b&igrave;nh thường, bạn đ&atilde; c&oacute; thể bắt trọn những khoảnh khắc vui vẻ b&ecirc;n cạnh bạn b&egrave;, người th&acirc;n.</p>

<p><img alt="Đánh giá camera trước iPhone 11" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-10.jpg" style="height:100%; width:100%" /></p>

<h3><strong>Pin tr&acirc;u, tương th&iacute;ch sạc kh&ocirc;ng d&acirc;y Qi</strong></h3>

<p>So với iPhone XR, iPhone 11 sở hữu pin dung lượng l&ecirc;n đến 3110 mAh đủ cho bạn thực hiện những t&aacute;c vụ th&ocirc;ng thường trong thời gian d&agrave;i. Bạn c&oacute; thể xem video thoải m&aacute;i l&ecirc;n đến 17 giờ, nghe nhạc ph&aacute;t lại l&ecirc;n đến 65 giờ, ph&aacute;t lại video trực tuyến đến 10 giờ.</p>

<p>Điện thoại hỗ trợ sạc kh&ocirc;ng d&acirc;y tương th&iacute;ch Qi, trong v&ograve;ng 30 ph&uacute;t bạn c&oacute; thể sạc được khoảng 50% pin với bộ sạc 20 W trở l&ecirc;n. Ngo&agrave;i ra, bạn cũng c&oacute; thể sạc qua USB v&agrave;o hệ thống m&aacute;y t&iacute;nh hoặc adapter.</p>

<p><img alt="Đánh giá pin iPhone 11" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-9.jpg" style="height:100%; width:100%" /></p>

<h3><strong>C&ocirc;ng nghệ nhận diện TrueDepth, chống nước chuẩn IP68</strong></h3>

<p>iPhone 11 sở hữu c&ocirc;ng nghệ nhận diện khu&ocirc;n mặt TrueDepth. Ngo&agrave;i ra, khi thanh to&aacute;n tại c&aacute;c cửa h&agrave;ng, ứng dụng kh&aacute;c nhau hay tr&ecirc;n tr&igrave;nh duyệt web, bạn ho&agrave;n to&agrave;n c&oacute; thể thanh to&aacute;n bằng Face ID.</p>

<p>B&ecirc;n cạnh đ&oacute;, về khả năng chống nước v&agrave; chống bụi, smartphone đến từ nh&agrave; Apple đạt xếp hạng IP68 theo chuẩ IEC 60529, tức l&agrave; chịu nước ở độ s&acirc;u tối đa 2 m&eacute;t trong v&ograve;ng 30 ph&uacute;t.</p>

<p><img alt="Các tính năng khác của iPhone 11" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-6.jpg" style="height:100%; width:100%" /></p>

<h2><strong>Điện thoại iPhone 11 c&oacute; mấy m&agrave;u?</strong></h2>

<p>Phi&ecirc;n bản của d&ograve;ng sản phẩm&nbsp;<strong>iPhone 11</strong>&nbsp;thường bao gồm 6 t&ugrave;y chọn m&agrave;u sắc chủ yếu: Đen, xanh mint, v&agrave;ng, t&iacute;m pastel, đỏ v&agrave; trắng tha hồ cho bạn lựa chọn ph&ugrave; hợp với phong c&aacute;ch của bản th&acirc;n.</p>

<h3><strong>M&agrave;u t&iacute;m pastel</strong></h3>

<p>iPhone đ&atilde; bắt kịp trend m&agrave;u sắc t&iacute;m nhẹ nh&agrave;ng, mộng mơ, độc đ&aacute;o nhưng kh&ocirc;ng k&eacute;m phần sang trọng, đem lại sự tươi mới cho t&iacute;n đồ nh&agrave; T&aacute;o khuyết. Theo thống k&ecirc; năm 2019, khi vừa mới ra mắt, phi&ecirc;n bản n&agrave;y đ&atilde; được đặt mua v&agrave; ch&aacute;y h&agrave;ng rất nhanh tr&ecirc;n to&agrave;n thế giới. Ch&iacute;nh m&agrave;u sắc nh&atilde; nhặn, thanh lịch m&agrave; n&oacute; đ&atilde; chiễm trọn tr&aacute;i tim của người d&ugrave;ng.</p>

<p><img alt="iPhone 11 màu tím pastel" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-mau-tim.jpg" style="height:100%; width:100%" /></p>

<h3><strong>M&agrave;u xanh mint (Xanh ngọc)</strong></h3>

<p>Mang đến cảm gi&aacute;c tươi m&aacute;t, đầy sảng kho&aacute;i, pha ch&uacute;t dịu d&agrave;ng, phi&ecirc;n bản iPhone 11 m&agrave;u xanh mint th&iacute;ch hợp cho những người y&ecirc;u th&iacute;ch sự đổi mới, nhẹ nh&agrave;ng v&agrave; c&oacute; ch&uacute;t l&atilde;ng mạn. M&agrave;u sắc sang trọng kết hợp với thiết kế tinh tế khiến chiếc smartphone n&agrave;y trở n&ecirc;n sang trọng hơn bao giờ hết.</p>

<p><img alt="iPhone 11 màu xanh mint" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-1.jpg" style="height:100%; width:100%" /></p>

<h3><strong>M&agrave;u trắng (White)</strong></h3>

<p>Chưa dừng lại ở 2 m&agrave;u nhẹ nh&agrave;ng, tươi mới tr&ecirc;n, iPhone tiếp tục l&agrave;m chao đảo người d&ugrave;ng kh&oacute; t&iacute;nh nhất với m&agrave;u trắng tinh kh&ocirc;i. Vẻ đẹp thời thượng của smartphone c&ugrave;ng m&agrave;u sắc thanh thuần rất hợp cho những bạn theo chủ nghĩa tối giản hoặc th&iacute;ch m&agrave;u đơn giản như trắng hoặc đen. Bạn c&oacute; thể thoải m&aacute;i lựa chọn những phụ kiện ốp lưng trong suốt, phụ kiện hoặc sticker đ&iacute;nh k&egrave;m l&ecirc;n iPhone.</p>

<p><img alt="iPhone 11 màu trắng" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-mau-trang.jpg" style="height:100%; width:100%" /></p>

<h3><strong>M&agrave;u đỏ (Product Red)</strong></h3>

<p>Nếu bạn l&agrave; người th&iacute;ch sự nổi bật, độc đ&aacute;o, c&aacute; t&iacute;nh v&agrave; thu h&uacute;t sự ch&uacute; &yacute; của những người xung quanh th&igrave; kh&ocirc;ng n&ecirc;n bỏ qua m&agrave;u đỏ quyến rũ. B&ecirc;n cạnh đ&oacute;, m&agrave;u đỏ c&ograve;n l&agrave; biểu trưng cho sự may mắn, ph&aacute;t đạt, đặc biệt l&agrave; những ai c&oacute; mệnh Hỏa hoặc l&agrave;m kinh doanh th&igrave; n&ecirc;n tậu ngay chiếc iPhone n&agrave;y về nh&eacute;!</p>

<p><img alt="iPhone 11 màu đỏ" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-12.jpg" style="height:100%; width:100%" /></p>

<h3><strong>M&agrave;u đen (Black)</strong></h3>

<p>Mạnh mẽ, c&aacute; t&iacute;nh, sang trọng l&agrave; những nhận x&eacute;t đầu ti&ecirc;n khi nhắc đến iPhone 13 m&agrave;u đen. Nếu c&aacute;nh m&agrave;y r&acirc;u đang ph&acirc;n v&acirc;n lựa chọn m&agrave;u n&agrave;o th&igrave; n&ecirc;n chọn ngay bởi vẻ đẹp h&agrave;o hoa, lịch thiệp m&agrave; m&agrave;u đen huyền b&iacute; mang lại. Ngo&agrave;i ra, m&agrave;u đen lại c&ograve;n rất dễ kết hợp với nhiều phụ kiện hoặc trang phục kh&aacute;c nhau.</p>

<p><img alt="iPhone 11 màu đen" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-mau-den.jpg" style="height:100%; width:100%" /></p>

<h3><strong>M&agrave;u v&agrave;ng (Yellow)</strong></h3>

<p>Những bạn y&ecirc;u th&iacute;ch phong c&aacute;ch tươi s&aacute;ng, c&aacute; t&iacute;nh, trẻ trung th&igrave; m&agrave;u v&agrave;ng l&agrave; sự lựa chọn ho&agrave;n hảo trong l&uacute;c n&agrave;y đấy. M&agrave;u tr&ecirc;n thực tế m&agrave;u v&agrave;ng sẽ hơi nhạt hơn một ch&uacute;t. Tuy chưa được đ&aacute;nh gi&aacute; cao như c&aacute;c m&agrave;u c&ograve;n lại nhưng sự t&iacute;ch cực v&agrave; cảm gi&aacute;c vui vẻ, tươi mới khiến người d&ugrave;ng vẫn chọn l&agrave;m chiếc T&aacute;o của ri&ecirc;ng m&igrave;nh.</p>

<p><img alt="iPhone 11 màu vàng" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-mau-vang.jpg" style="height:100%; width:100%" /></p>

<h2><strong>Dung lượng iPhone 11 bao nhi&ecirc;u GB?</strong></h2>

<p>iPhone 11 c&oacute; 2 phi&ecirc;n bản dung lượng l&agrave; 64GB, 128GB v&agrave; 256GB. Dung lượng 64GB l&agrave; phi&ecirc;n bản được nhiều người hướng đến với mức gi&aacute; hấp dẫn. Nếu bạn chỉ sử dụng c&aacute;c t&aacute;c vụ như nghe gọi, nhắn tin, xem video v&agrave; thực hiện c&aacute;c t&aacute;c vụ đơn giản kh&aacute;c th&igrave; n&ecirc;n d&ugrave;ng loại 64GB.</p>

<p>Trong trường hợp bạn cần thực hiện lưu trữ c&aacute;c t&agrave;i liệu phim ảnh số lượng lớn, quay nhiều video chất lượng cao m&agrave; 64GB kh&ocirc;ng đủ th&igrave; n&ecirc;n sử dụng phi&ecirc;n bản 128GB hoặc 256GB.</p>

<h2><strong>Điện thoại iPhone 11 c&oacute; mấy sim?</strong></h2>

<p>Sản phẩm được hỗ trợ 2 dạng phi&ecirc;n bản sim lần lượt l&agrave;:</p>

<ul>
	<li>
	<p>&bull;&nbsp;&nbsp;2 SIM vật l&yacute;</p>
	</li>
	<li>
	<p>&bull;&nbsp;&nbsp;1 SIM vật l&yacute; v&agrave; eSIM</p>
	</li>
</ul>

<p>Phi&ecirc;n bản hỗ trợ eSIM c&oacute; khả năng kết nối được với c&aacute;c nh&agrave; mạng lớn ở Việt Nam như Vinaphone, Viettel v&agrave; Mobifone. C&ograve;n phi&ecirc;n bản 2 SIM vật l&yacute; mới chỉ d&agrave;nh cho khu vực Macao v&agrave; Hồng K&ocirc;ng, Việt Nam chưa được hỗ trợ.</p>

<p><img alt="Điện thoại iPhone 11 có mấy sim?" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-7.jpg" style="height:100%; width:100%" /></p>

<h2><strong>iPhone 11 gi&aacute; bao nhi&ecirc;u tiền 2021?</strong></h2>

<p>L&agrave; si&ecirc;u phẩm được chờ đợi nhất năm 2019, iPhone 11 dao động từ 19.5 triệu đến khoảng 23 triệu t&ugrave;y theo c&aacute;c phi&ecirc;n bản kh&aacute;c nhau. Cho tới thời điểm hiện tại, gi&aacute; tại CellphoneS như sau:</p>

<ul>
	<li>
	<p>&bull;&nbsp;&nbsp;Phi&ecirc;n bản 256GB: Khoảng 20.500.000 đồng</p>
	</li>
	<li>
	<p>&bull;&nbsp;&nbsp;Phi&ecirc;n bản 128GB: Khoảng 19.000.000 đồng</p>
	</li>
	<li>
	<p>&bull;&nbsp;&nbsp;Phi&ecirc;n bản 64GB: Khoảng 16.900.000 đồng</p>
	</li>
</ul>

<p>T&ugrave;y theo nhu cầu kh&aacute;c nhau, bạn c&oacute; thể lựa chọn cho m&igrave;nh m&agrave;u sắc v&agrave; phi&ecirc;n bản dung lượng ph&ugrave; hợp.</p>

<h2>Điện thoại iPhone 11 ra mắt khi n&agrave;o?</h2>

<p>iPhone 11 ph&aacute;t h&agrave;nh lần đầu v&agrave;o ng&agrave;y 19 th&aacute;ng 9 năm 2019 v&agrave; ch&iacute;nh thức mở b&aacute;n từ ng&agrave;y 20/9/2019. Trước ng&agrave;y 13/9, Apple đ&atilde; cho đặt trước. iPhone được đ&aacute;nh gi&aacute; cao với sức mua khủng từ thị trường, đặc biệt l&agrave; Trung Quốc.</p>

<p><img alt="Tại sao nên mua iPhone 11 chính hãng VN/A?" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iphone-11-anh-15.jpg" style="height:100%; width:100%" /></p>

<h2><strong>Tại sao n&ecirc;n mua iPhone 11 ch&iacute;nh h&atilde;ng VN/A?</strong></h2>

<p>Cho đến hiện tại, iPhone năm 2019 vẫn đang l&agrave; sản phẩm cực kỳ tiềm năng, ấn tượng v&agrave; đ&aacute;ng mua cho đến thời điểm hiện tại. Kh&ocirc;ng những c&oacute; cấu h&igrave;nh với chipset mạnh mẽ, hiệu năng cực ổn, si&ecirc;u phẩm c&ograve;n mang thiết kế ấn tượng, camera chống rung quang học cho h&igrave;nh ảnh sắc n&eacute;t, ch&acirc;n thực.&nbsp;Nếu bạn c&oacute; kinh ph&iacute; chưa đủ để mua iPhone 12 th&igrave; ho&agrave;n to&agrave;n c&oacute; thể mua iPhone 11.</p>

<p>Khi mua iPhone 11 ch&iacute;nh h&atilde;ng VN/A, bạn sẽ nhận được chế độ bảo h&agrave;nh 12 th&aacute;ng tại c&aacute;c trung t&acirc;m bảo h&agrave;nh ủy quyền của Apple tại Việt Nam.&nbsp;Sản phẩm ch&iacute;nh h&atilde;ng cũng kh&ocirc;ng tiềm ẩn nhiều rủi ro về chất lượng so với khi bạn mua h&agrave;ng x&aacute;ch tay kh&ocirc;ng r&otilde; nguồn gốc.</p>

<h2><strong>Mua iPhone 11 gi&aacute; rẻ, ưu đ&atilde;i khủng tại CellphoneS</strong></h2>

<p>Bạn đang ph&acirc;n v&acirc;n kh&ocirc;ng biết n&ecirc;n mua&nbsp;<strong>điện thoại iPhone 11</strong>&nbsp;VN/A ở đ&acirc;u chất lượng, gi&aacute; tốt? CellphoneS rất sẵn l&ograve;ng được phục vụ v&agrave; tư vấn chi tiết th&ocirc;ng số kỹ thuật, c&aacute;c vướng mắc bạn đang gặp phải. Ch&uacute;ng t&ocirc;i cam kết h&agrave;ng ch&iacute;nh h&atilde;ng Apple với đầy đủ chế độ bảo h&agrave;nh với gi&aacute; ưu đ&atilde;i, giảm gi&aacute; li&ecirc;n tục.</p>
', N'iPhone 11 I Chính hãng VN/A', 14990000, 1)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (2, NULL, NULL, CAST(N'2022-01-06T11:44:28.260' AS DateTime), N'admin', N'1641444268130_galaxy-z-fold3.png', N'<p>M&ocirc; tả</p>
', N'Samsung Galaxy Z Fold3 5G', 41990000, 1)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (3, NULL, NULL, CAST(N'2022-01-06T11:44:45.887' AS DateTime), N'admin', N'1641444285764_man-hinh-samsung-28-inch-lu28r55.png', N'<p>M&ocirc; tả</p>
', N'Màn hình Samsung 28 inch LU28R550UQEXXV', 7990000, 5)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (15, CAST(N'2021-12-24T14:14:23.597' AS DateTime), N'admin', CAST(N'2021-12-24T14:14:23.597' AS DateTime), N'admin', N'1640330063519_ip13.png', N'Đánh giá iPhone 13 - Flagship được mong chờ năm 2021', N'iPhone 13 | Chính hãng VN/A', 23000000, 1)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (16, CAST(N'2021-12-24T14:27:13.880' AS DateTime), N'admin', CAST(N'2021-12-24T14:27:13.880' AS DateTime), N'admin', N'1640330833794_ipad-air-2020-silver_6.png', N'iPad Air 4 2020 – Chip A14 mạnh mẽ cho trải nghiệm hoàn hảo', N'iPad Air 10.9 2020 WiFi 64GB I Chính hãng Apple Việt Nam', 16990000, 3)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (17, CAST(N'2021-12-24T14:33:41.287' AS DateTime), N'admin', CAST(N'2022-01-06T11:46:01.270' AS DateTime), N'admin', N'1641444360889_macbook-air-gold-select-2018.png', N'<p>Macbook Air M1 2020 - Sang trọng, tinh tế, hiệu năng khủng</p>
', N'Apple MacBook Air M1 256GB 2020 I Chính hãng Apple Việt Nam', 25700000, 2)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (18, CAST(N'2021-12-24T15:03:35.433' AS DateTime), N'admin', CAST(N'2021-12-24T15:03:35.433' AS DateTime), N'admin', N'1640333015345_microsoft_surface_pro-02_3.png', N'Microsoft Surface Pro 7 i5/8/128 – Phù hợp công việc và giải trí cơ bản', N'Surface Pro 7 Core i5 / 8GB / 128GB Chính Hãng', 20690000, 2)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (19, CAST(N'2021-12-25T13:48:25.887' AS DateTime), N'admin', CAST(N'2021-12-25T13:52:19.590' AS DateTime), N'admin', N'1640415139550_iphone-12-pro-max.png', N'iPhone 12 Pro Max 256GB VN/A – Dung lượng lưu trữ nâng cấp', N'iPhone 12 Pro Max 256GB  I Chính hãng VN/A', 30500000, 1)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (21, CAST(N'2021-12-25T13:53:44.737' AS DateTime), N'admin', CAST(N'2021-12-25T16:55:58.347' AS DateTime), N'admin', N'1640426158323_xiaomiredmi10.png', N'<p>Trải nghiệm chất lượng hiển thị sắc n&eacute;t, mượt m&agrave; - M&agrave;n h&igrave;nh 6.5&quot; FullHD+, tần số qu&eacute;t 90 Hz</p>
', N'Xiaomi Redmi 10 (4GB - 64GB)', 3990000, 1)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (23, CAST(N'2021-12-25T14:11:03.040' AS DateTime), N'admin', CAST(N'2021-12-25T16:29:52.603' AS DateTime), N'admin', N'1640424592511_samsung-galaxy-tab-s7-plus-3.png', N'<h2>Samsung Galaxy Tab S7+ : M&agrave;n h&igrave;nh rộng sắc n&eacute;t, pin khủng 10.090mAh</h2>

<p>M&aacute;y t&iacute;nh bảng giờ đ&acirc;y đ&atilde; trở th&agrave;nh một sản phẩm c&ocirc;ng nghệ quen thuộc v&agrave; quan trọng đối với rất nhiều người. Ng&agrave;y qua ng&agrave;y, người d&ugrave;ng đều mong muốn nhu cầu sử dụng của họ sẽ tốt hơn v&agrave; đ&ograve;i hỏi cao hơn về một chiếc tablet Samsung. Thấu hiểu điều đ&oacute;, tập đo&agrave;n Samsung đ&atilde; cho ra mắt&nbsp;<a href="https://cellphones.com.vn/samsung-galaxy-tab-s7.html" target="_blank">m&aacute;y t&iacute;nh bảng Samsung Galaxy Tab S7</a>&nbsp;v&agrave;&nbsp;<strong>Tab S7 Plus</strong>. Đ&acirc;y được xem l&agrave; chiếc tablet được trang bị những t&iacute;nh năng hiện đại bậc nhất c&ugrave;ng thời lượng pin cực khủng, gi&uacute;p giải quyết nỗi lo hụt pin cho cả ng&agrave;y sử dụng.</p>

<h3>Thiết kế cứng c&aacute;p v&agrave; sang trọng c&ugrave;ng m&agrave;n h&igrave;nh sAMOLED 12.4 inch</h3>

<p>Chiếc Samsung Tab S7 Plus l&agrave; sản phẩm rất được Samsung ưu &aacute;i, sở hữu thiết kế cứng c&aacute;p với chất liệu kim loại s&aacute;ng b&oacute;ng v&agrave; c&aacute;c g&oacute;c được bo cong một c&aacute;ch ho&agrave;n hảo. Mang đến cho sản phẩm kh&ocirc;ng chỉ l&agrave; sự cứng c&aacute;p, m&agrave; được h&ograve;a hợp tuyệt vời c&ugrave;ng những đường n&eacute;t mềm mại.</p>

<p>Tham khảo th&ecirc;m:&nbsp;<a href="https://cellphones.com.vn/samsung-galaxy-tab-s7-lite.html">Samsung Galaxy Tab S7 Lite</a></p>

<p><img alt="Thiết kế cứng cáp và sang trọng cùng màn hình AMOLED 12.4 inch" src="https://cdn.cellphones.com.vn/media/wysiwyg/tablet/samsung/galaxy-tab-s7-plus-1.jpg" style="height:100%; width:100%" /></p>

<p>M&agrave;n h&igrave;nh Amoled 12.4 inch v&agrave; độ ph&acirc;n giải l&ecirc;n đến 2K+, kh&ocirc;ng chỉ mang đến cho bạn một m&agrave;n h&igrave;nh lớn dễ d&agrave;ng quan s&aacute;t v&agrave; thao t&aacute;c m&agrave; c&ograve;n c&oacute; chất lượng hiển thị rất r&otilde; r&agrave;ng, mọi chi tiết đều sắc n&eacute;t, sống động, cho bạn những ph&uacute;t gi&acirc;y thưởng thức h&igrave;nh ảnh tốt nhất.</p>

<p><img alt="Nhiều màu sắc lựa chọn" src="https://cdn.cellphones.com.vn/media/wysiwyg/tablet/samsung/galaxy-tab-s7-plus-2.jpg" style="height:100%; width:100%" /></p>

<h3>Trang bị cảm biến v&acirc;n tay v&agrave; b&uacute;t S-Pen hiện đại v&agrave; camera sau k&eacute;p</h3>

<p><strong>Galaxy Tab S7 Plus</strong>&nbsp;được trang bị bảo mật cảm biến v&acirc;n tay dưới m&agrave;n h&igrave;nh, gi&uacute;p bạn dễ d&agrave;ng v&agrave; nhanh ch&oacute;ng đăng nhập hơn, đồng thời cho khả năng bảo mật tốt hơn, người kh&aacute;c sẽ kh&oacute; c&oacute; thể mở được m&aacute;y t&iacute;nh bảng của bạn nếu kh&ocirc;ng d&ugrave;ng v&acirc;n tay của bạn.</p>

<p><img alt="Trang bị cảm biến vân tay và bút S-Pen hiện đại và camera sau kép" src="https://cdn.cellphones.com.vn/media/wysiwyg/tablet/samsung/galaxy-tab-s7-plus-3.jpg" style="height:100%; width:100%" /></p>

<p>Kh&ocirc;ng chỉ thế,&nbsp;<a href="https://cellphones.com.vn/tablet/samsung.html" target="_blank">m&aacute;y t&iacute;nh bảng Samsung</a>&nbsp;Galaxy Tab S7 Plus kh&ocirc;ng thể thiếu S-Pen hiện đại gi&uacute;p mọi thao t&aacute;c lu&ocirc;n c&oacute; độ nhạy tốt, bạn c&oacute; thể mở tablet v&agrave; ghi ch&uacute; nhanh với b&uacute;t S-Pen thay v&igrave; phải g&otilde; sẽ chậm hơn rất nhiều. Ngo&agrave;i ra, Tab S7+ c&ograve;n c&oacute; bộ đ&ocirc;i camera sau với độ ph&acirc;n giải 13MP v&agrave; 5MP v&agrave; camera trước 8MP n&ecirc;n ho&agrave;n to&agrave;n đủ khả ăng đ&aacute;p ứng nhu cầu chụp ảnh c&oacute; chất lượng tốt.</p>

<p><img alt="camera sau kép" src="https://cdn.cellphones.com.vn/media/wysiwyg/tablet/samsung/galaxy-tab-s7-plus-6.jpg" style="height:100%; width:100%" /></p>

<h3>Trang bị chip Snapdragon 865+ mạnh mẽ v&agrave; RAM 6GB, 128GB bộ nhớ trong</h3>

<p><strong>Samsung Tab S7 Plus</strong>&nbsp;được trang bị chip Snapdragon 865+ mới nhất hiện nay, vừa được Qualcomm cho ra mắt v&agrave;o 9/7. Với bộ vi xử l&iacute; n&agrave;y, chắc hẳn Tab S7 Plus sẽ mang đến khả năng xử l&iacute; cực k&igrave; mạnh mẽ v&agrave; nhanh ch&oacute;ng ngay cả khi xử l&iacute; đa nhiệm.</p>

<p><img alt="Trang bị chip Snapdragon 865+ mạnh mẽ và RAM 6GB, 128GB bộ nhớ trong" src="https://cdn.cellphones.com.vn/media/wysiwyg/tablet/samsung/galaxy-tab-s7-plus-4.jpg" style="height:100%; width:100%" /></p>

<p>Ngo&agrave;i ra, chiếc m&aacute;y t&iacute;nh bảng n&agrave;y c&ograve;n sở hữu RAM 6GB v&agrave; bộ nhớ trong 128GB cho ph&eacute;p hỗ trợ vi xử l&iacute; hoạt động hiệu quả v&agrave; năng suất hơn, đồng thời bộ nhớ trong 128GB sẽ mở ra khả năng lưu trữ cực lớn cho người d&ugrave;ng, h&igrave;nh ảnh, video hay c&aacute;c ghi ch&uacute;, ứng dụng lớn đều kh&ocirc;ng th&agrave;nh vấn đề.</p>

<h3>Dung lượng pin l&ecirc;n đến 10.090 mAh c&ugrave;ng c&ocirc;ng nghệ sạc nhanh 45W</h3>

<p>Điểm nhấn v&ocirc; c&ugrave;ng nổi bật của Samsung Galaxy Tab S7 Plus ch&iacute;nh l&agrave; dung lượng pin đạt đến con số cực khủng 10.090 mAh. Đ&acirc;y l&agrave; mức pin rất cao d&agrave;nh cho một chiếc tablet so với những sản phẩm c&oacute; mặt tr&ecirc;n thị trường hiện nay. Với mức pin n&agrave;y thậm ch&iacute; bạn c&oacute; thể xem chiếc m&aacute;y t&iacute;nh bảng n&agrave;y như một chiếc sạc dự ph&ograve;ng để sạc pin cho smartphone khi cấp thiết.</p>

<p><img alt="Dung lượng pin lên đến 10.000 mAh cùng công nghệ sạc nhanh 45W" src="https://cdn.cellphones.com.vn/media/wysiwyg/tablet/samsung/galaxy-tab-s7-plus-5.jpg" style="height:100%; width:100%" /></p>

<p>Ngo&agrave;i ra, sở hữu c&ocirc;ng nghệ sạc nhanh 45W gi&uacute;p Samsung Tab S7 Plus c&oacute; thể sạc đầy chỉ với thời gian ngắn, tiết kiệm thời gian để bạn c&oacute; thể nhanh ch&oacute;ng tiếp tục sử dụng v&agrave; giải quyết c&ocirc;ng việc.</p>

<h2>Mua m&aacute;y t&iacute;nh bảng Samsung Galaxy Tab S7 Plus gi&aacute; tốt tại CellphoneS</h2>

<p>Nếu bạn đ&atilde; bị &ldquo;hớp hồn&rdquo; bởi chiếc&nbsp;<strong>Samsung Galaxy Tab S7+&nbsp;</strong>ch&iacute;nh h&atilde;ng v&agrave; mong muốn nhanh ch&oacute;ng sở hữu được n&oacute;, nhưng cần một địa chỉ c&oacute; thể đảm bảo về chất lượng của sản phẩm, th&igrave; h&atilde;y đến ngay CellphoneS. L&agrave; hệ thống b&aacute;n lẻ smartphone, m&aacute;y t&iacute;nh bảng, laptop h&agrave;ng đầu tại H&agrave; Nội v&agrave; TPHCM, CellphoneS lu&ocirc;n đảm bảo về chất lượng sản phẩm cung cấp cho kh&aacute;ch h&agrave;ng c&ugrave;ng với mức gi&aacute; tốt.</p>

<p>Kh&ocirc;ng chỉ c&oacute; thế, bạn c&oacute; thể li&ecirc;n hệ với CellphoneS để nhận được sự tư vấn về sản phẩm hoặc theo d&otilde;i tr&ecirc;n trang web của hệ thống. Kh&ocirc;ng chỉ mua được h&agrave;ng ch&iacute;nh h&atilde;ng với mức gi&aacute; &ldquo;y&ecirc;u thương&rdquo; khi đến CellphoneS, m&agrave; bạn c&ograve;n c&oacute; thể c&oacute; thể nhận được qu&agrave; tặng v&agrave; ưu đ&atilde;i &ldquo;khủng&rdquo;. Ngo&agrave;i ra, bạn cũng c&oacute; thể tham khảo&nbsp;<a href="https://cellphones.com.vn/samsung-galaxy-tab-s8-plus.html">Samsung Galaxy Tab S8 Plus</a>&nbsp;mới ra mắt với nhiều n&acirc;ng cấp về cấu h&igrave;nh v&agrave; dung lượng pin.</p>
', N'Samsung Galaxy Tab S7 Plus', 22500000, 3)
INSERT [dbo].[product] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [avatar], [description], [name], [price], [category_id]) VALUES (33, CAST(N'2022-01-06T17:38:13.767' AS DateTime), N'admin', CAST(N'2022-01-06T17:40:17.387' AS DateTime), N'admin', N'1641465493629_iphone-12.png', N'<h2><strong>Apple iPhone 12 128GB ch&iacute;nh h&atilde;ng (VN/A) phi&ecirc;n bản bộ nhớ 128GB lưu trữ thoải&nbsp;</strong><strong>m&aacute;i</strong></h2>

<p><em>iPhone 12 hiện nay l&agrave; c&aacute;i t&ecirc;n &ldquo;sốt x&igrave;nh xịch&rdquo; bởi đ&acirc;y l&agrave; một trong 4 si&ecirc;u phẩm vừa được ra mắt của Apple với những đột ph&aacute; về cả thiết kế lẫn cấu h&igrave;nh. Phi&ecirc;n bản Apple iPhone 12 128GB ch&iacute;nh l&agrave; một trong những chiếc iPhone được săn đ&oacute;n nhất bởi dung lượng bộ nhớ khủng, cho ph&eacute;p bạn thoải m&aacute;i với v&ocirc; v&agrave;n ứng dụng.</em></p>

<h3><strong>Dung lượng bộ nhớ trong l&ecirc;n đến 128GB v&agrave; chip Apple A14 độc quyền</strong></h3>

<p><a href="https://cellphones.com.vn/iphone-12.html">iPhone 12 VN/A</a>&nbsp;phi&ecirc;n bản bộ nhớ trong 128GB sẽ khiến bạn c&oacute; thể v&ocirc; tư với h&agrave;ng t&aacute; ứng dụng, chứa rất nhiều ảnh, video v&agrave; kh&ocirc;ng cần lo lắng việc kh&ocirc;ng đủ dung lượng sử dụng.</p>

<p><img alt="Dung lượng bộ nhớ trong lên đến 128GB và chip Apple A14 độc quyền" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iPhone-12-128gb.jpg" style="height:100%" /></p>

<p>Chip A14 độc quyền từ Apple mang đến cho iPhone 12 hiệu năng mạnh mẽ, xử l&iacute; t&aacute;c vụ nhanh ch&oacute;ng v&agrave; giữ m&aacute;y lu&ocirc;n ổn định.</p>

<h3><strong>Thiết kế độc đ&aacute;o với viền v&aacute;t phẳng mạnh mẽ v&agrave; hỗ trợ sạc nhanh 20W</strong></h3>

<p>Kh&ocirc;ng chỉ thế, thiết kế của iPhone 12 c&oacute; sự đột ph&aacute; với viền m&aacute;y v&aacute;t phẳng mạnh mẽ, c&aacute; t&iacute;nh. Kh&ocirc;ng c&ograve;n bo cong mềm mại như c&aacute;c thế hệ iPhone trước.</p>

<p><img alt="Thiết kế độc đáo với viền vát phẳng mạnh mẽ và hỗ trợ sạc nhanh 20W" src="https://cdn.cellphones.com.vn/media/wysiwyg/mobile/apple/iPhone-12-128gb-2.jpg" style="height:100%" /></p>

<p>Ngo&agrave;i ra, iPhone 12 128GB ch&iacute;nh h&atilde;ng (VN/A) c&ograve;n hỗ trợ sạc nhanh l&ecirc;n đến 20W, tiết kiệm thời gian của bạn v&agrave; c&oacute; thể nhanh ch&oacute;ng tiếp tục sử dụng điện thoại của m&igrave;nh.</p>
', N'iPhone 12 128GB I Chính hãng VN/A', 20500000, 1)
SET IDENTITY_INSERT [dbo].[product] OFF
GO
SET IDENTITY_INSERT [dbo].[role] ON 

INSERT [dbo].[role] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [code], [name]) VALUES (1, NULL, NULL, NULL, NULL, N'ADMIN', N'Quản trị')
INSERT [dbo].[role] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [code], [name]) VALUES (2, NULL, NULL, NULL, NULL, N'USER', N'Người dùng')
SET IDENTITY_INSERT [dbo].[role] OFF
GO
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (1, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (2, 1)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (3, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (14, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (18, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (19, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (13, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (34, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (43, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (44, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (15, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (16, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (35, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (36, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (42, 2)
INSERT [dbo].[user_role] ([user_id], [role_id]) VALUES (40, 2)
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (1, NULL, NULL, NULL, NULL, N'Hà Nội', N'admin.jpg', CAST(N'2001-10-14T00:00:00.000' AS DateTime), N'tupham1120@gmail.com', N'', N'Phạm Lê Việt Tú', N'Nam', N'Sinh viên', N'$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', N'0966871026', 1, N'admin', 1)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (2, NULL, NULL, NULL, NULL, N'', N'', CAST(N'2000-12-12T00:00:00.000' AS DateTime), N'', N'', N'Nguyễn Văn A', N'Nữ', N'', N'$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', N'', 1, N'nguyenvana', 2)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (3, NULL, NULL, NULL, NULL, N'', N'', CAST(N'1999-11-11T00:00:00.000' AS DateTime), N'', N'', N'Nguyễn Văn B', N'Nam', N'', N'$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', N'', 1, N'nguyenvanb', 3)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (13, CAST(N'2021-12-21T18:42:10.807' AS DateTime), N'anonymousUser', CAST(N'2022-01-02T10:35:05.377' AS DateTime), N'admin', N'Hà Nội', N'', CAST(N'2000-11-11T00:00:00.000' AS DateTime), N'user1@gmail.com', N'', N'User 1', N'Nam', N'', N'$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', N'0966871025', 1, N'user1', 4)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (14, CAST(N'2021-12-21T18:47:34.103' AS DateTime), N'anonymousUser', CAST(N'2021-12-21T18:47:34.103' AS DateTime), N'anonymousUser', N'', N'', CAST(N'1999-10-10T00:00:00.000' AS DateTime), N'', N'', N'', N'', N'', N'$2a$10$.cWRfK0.44tiVTkAiCcavO9iGwqwh0.qgtigjHI9phhCpxQ23eOsC', N'', 1, N'user2', 5)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (15, CAST(N'2021-12-22T14:01:06.057' AS DateTime), N'anonymousUser', CAST(N'2021-12-22T14:01:06.057' AS DateTime), N'anonymousUser', N'', N'', CAST(N'1999-10-10T00:00:00.000' AS DateTime), N'', N'', N'', N'', N'', N'$2a$10$tLDARnfcn0ybWiE90JxcXONuNCeKAKyHntqRC3VLR7GtVcyNrMor6', N'', 1, N'user3', 6)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (16, CAST(N'2021-12-22T14:34:39.667' AS DateTime), N'anonymousUser', CAST(N'2021-12-22T14:34:39.667' AS DateTime), N'anonymousUser', N'', N'', CAST(N'1999-10-10T00:00:00.000' AS DateTime), N'', N'', N'', N'', N'', N'$2a$10$LOQxfx8GSvBMhj5OJLqnYuyf05ZgwfX2aBCIZuwu8XzbJIkJ9.o1K', N'', 1, N'user4', 7)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (18, CAST(N'2021-12-22T14:55:16.650' AS DateTime), N'anonymousUser', CAST(N'2021-12-22T14:55:16.650' AS DateTime), N'anonymousUser', N'', N'avatar.jpg', CAST(N'1999-10-10T00:00:00.000' AS DateTime), N'user5@gmail.com', N'', N'User 5', N'Nữ', N'', N'$2a$10$zIZLPkMZXllr6qsv5kKq5O2uH8uO6gYp5NpcAjW5gcZjFlPTkwH7G', N'', 1, N'user5', 8)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (19, CAST(N'2021-12-22T15:16:24.480' AS DateTime), N'anonymousUser', CAST(N'2022-01-12T09:45:31.903' AS DateTime), N'admin', N'', N'', CAST(N'1999-10-10T00:00:00.000' AS DateTime), N'user6@gmail.com', N'', N'User 6', N'', N'', N'$2a$10$jJPhSPFkcRkqyuTn6eYnCO/kfowBwN2dy2K6xWsFWfrkVlDkSlR0m', N'0213654789', 0, N'user6', 9)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (34, CAST(N'2022-01-12T15:20:01.207' AS DateTime), N'anonymousUser', CAST(N'2022-01-12T15:20:56.193' AS DateTime), N'anonymousUser', NULL, N'', NULL, N'user7@gmail.com', NULL, N'User7', NULL, NULL, N'$2a$10$LxkI6BYmMaPvFw5BPf4aiekMNELp9YTcaGIEhX3RplbMV.I7KCSj.', N'0966871022', 1, N'user7', 10)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (35, CAST(N'2022-01-12T15:21:33.707' AS DateTime), N'anonymousUser', CAST(N'2022-01-12T15:21:33.707' AS DateTime), N'anonymousUser', NULL, N'', NULL, N'user8@gmail.com', NULL, N'User8', NULL, NULL, N'$2a$10$ak/m8D4clDKNDE4szQ1MUOf3P9zsx7j1qHLIzrJNJQPMLTD0rgDP.', N'0966871055', 1, N'user8', 11)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (36, CAST(N'2022-01-12T15:30:03.573' AS DateTime), N'anonymousUser', CAST(N'2022-01-12T15:30:03.573' AS DateTime), N'anonymousUser', N'', N'', NULL, N'user9@gmail.com', N'', N'User 9', N'', N'', N'$2a$10$Vpm/NIaWZhU8z2ksvqNjH.4j/AosNOmFotsEqbkl1IDy1lm3HbU1u', N'0966871088', 1, N'user9', 12)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (40, CAST(N'2022-01-12T15:53:05.803' AS DateTime), N'anonymousUser', CAST(N'2022-01-15T14:17:44.030' AS DateTime), N'anonymousUser', N'', N'1642231063402_heartburs.gif', CAST(N'1999-10-10T00:00:00.000' AS DateTime), N'tupham14102001@gmail.com', N'', N'User 10', N'Nam', N'', N'$2a$10$u/qeOOD4xkoP12nLFSME..s9pbcLB2kHzj3xdtr6CkAoJyrzQejdS', N'0852369741', 1, N'user10', 13)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (42, CAST(N'2022-01-12T18:32:31.090' AS DateTime), N'anonymousUser', CAST(N'2022-01-12T18:32:31.090' AS DateTime), N'anonymousUser', N'', N'', CAST(N'2022-01-12T00:00:00.000' AS DateTime), N'user11@gmail.com', N'', N'user11', N'', N'', N'$2a$10$4BK5Fkweyum48MqxMZgwCeo2umN5wFpG8YXvRGIp8nCUZUiW8XJPe', N'0088552211', 1, N'user11', 14)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (43, CAST(N'2022-01-13T10:13:29.483' AS DateTime), N'anonymousUser', CAST(N'2022-01-13T10:13:29.483' AS DateTime), N'anonymousUser', NULL, N'1642043609205_b7deb8e9b8dc43821acd.jpg', NULL, N'user12@gmail.com', NULL, NULL, NULL, NULL, N'$2a$10$KTaeILz02tF3/1O/T.KFAOwsJ3f7jzTLZnu0n0FQFbP6XORcMmTq6', N'0112255447', 1, N'user12', 15)
INSERT [dbo].[users] ([id], [createdAt], [createdBy], [updatedAt], [updatedBy], [address], [avatar], [birthday], [email], [facebook], [fullname], [gender], [jobs], [password], [phone], [status], [username], [cart_id]) VALUES (44, CAST(N'2022-01-13T10:15:58.503' AS DateTime), N'anonymousUser', CAST(N'2022-01-13T10:15:58.503' AS DateTime), N'anonymousUser', NULL, N'1642043758399_unnamed.jpg', NULL, N'user13@gmail.com', NULL, NULL, NULL, NULL, N'$2a$10$eLHJSEaxPGTiuotgIgCcwO6MBKaf/y33JsXDB4KhbdkjedvByZVP6', N'0225588990', 1, N'user13', 16)
SET IDENTITY_INSERT [dbo].[users] OFF
GO
ALTER TABLE [dbo].[cart_product]  WITH CHECK ADD  CONSTRAINT [FK_1y7riaqs4iffor59hddfmyod8] FOREIGN KEY([cart_id])
REFERENCES [dbo].[cart] ([id])
GO
ALTER TABLE [dbo].[cart_product] CHECK CONSTRAINT [FK_1y7riaqs4iffor59hddfmyod8]
GO
ALTER TABLE [dbo].[cart_product]  WITH CHECK ADD  CONSTRAINT [FK_23mn69dwnudyul90odbyx1oia] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[cart_product] CHECK CONSTRAINT [FK_23mn69dwnudyul90odbyx1oia]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD  CONSTRAINT [FK_mxoojfj9tmy8088avf57mpm02] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[comment] CHECK CONSTRAINT [FK_mxoojfj9tmy8088avf57mpm02]
GO
ALTER TABLE [dbo].[comment]  WITH CHECK ADD  CONSTRAINT [FK_qadidjn2h0dqbc1nseh5bo4ff] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[comment] CHECK CONSTRAINT [FK_qadidjn2h0dqbc1nseh5bo4ff]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_nkb731c2u3fxwuln18o6lyx6u] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_nkb731c2u3fxwuln18o6lyx6u]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FK_q6bwjb8y711vixrxinko7wmnw] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FK_q6bwjb8y711vixrxinko7wmnw]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_k8kupdtcdpqd57b6j4yq9uvdj] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_k8kupdtcdpqd57b6j4yq9uvdj]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK_rlaghtegr0yx2c1q1s6nkqjlh] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK_rlaghtegr0yx2c1q1s6nkqjlh]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FK_apcc8lxk2xnug8377fatvbn04] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FK_apcc8lxk2xnug8377fatvbn04]
GO
ALTER TABLE [dbo].[user_role]  WITH CHECK ADD  CONSTRAINT [FK_it77eq964jhfqtu54081ebtio] FOREIGN KEY([role_id])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[user_role] CHECK CONSTRAINT [FK_it77eq964jhfqtu54081ebtio]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK_pnp1baae4enifkkuq2cd01r9l] FOREIGN KEY([cart_id])
REFERENCES [dbo].[cart] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK_pnp1baae4enifkkuq2cd01r9l]
GO
/****** Object:  StoredProcedure [dbo].[insertDuplicateCartIdAndProductIdInCartProduct]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[insertDuplicateCartIdAndProductIdInCartProduct](@cartId BIGINT, @productId BIGINT, @quantity INT)
AS
BEGIN
    IF(@cartId IN (SELECT cart_id FROM dbo.cart_product) AND @productId IN (SELECT product_id FROM dbo.cart_product))
	BEGIN
	    UPDATE dbo.cart_product
		SET quantity = quantity + @quantity
		WHERE cart_id  = @cartId AND product_id = @productId
	END
	IF (@cartId IN (SELECT cart_id FROM dbo.cart_product) AND @productId NOT IN (SELECT product_id FROM dbo.cart_product))
	BEGIN
	    INSERT INTO dbo.cart_product (cart_id, product_id, quantity)
	    VALUES (@cartId, @productId, @quantity)
	END
	IF (@cartId NOT IN (SELECT cart_id FROM dbo.cart_product) AND @productId IN (SELECT product_id FROM dbo.cart_product))
	BEGIN
	    INSERT INTO dbo.cart_product (cart_id, product_id, quantity)
	    VALUES (@cartId, @productId, @quantity)
	END
	IF (@cartId NOT IN (SELECT cart_id FROM dbo.cart_product) AND @productId NOT IN (SELECT product_id FROM dbo.cart_product))
	BEGIN
	    INSERT INTO dbo.cart_product (cart_id, product_id, quantity)
	    VALUES (@cartId, @productId, @quantity)
	END
END
GO
/****** Object:  StoredProcedure [dbo].[insertTblOrderAndTblOrderDetail]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[insertTblOrderAndTblOrderDetail]
			(@userId BIGINT, @fullname NVARCHAR(255), @address NVARCHAR(255), @phone VARCHAR(12),
			 @note NVARCHAR(255), @price_total FLOAT)
AS
BEGIN
	DECLARE @email VARCHAR(255)
	SELECT @email = email FROM dbo.users WHERE id = @userId

	DECLARE @cartId BIGINT
	SELECT @cartId = cart_id FROM dbo.users WHERE id = @userId

    INSERT INTO dbo.orders
		(createdAt, updatedAt, address, email, fullname, note, phone, status, user_id, price_total)
    VALUES
		(GETDATE(), GETDATE(), @address, @email, @fullname, @note, @phone, 0, @userId, @price_total) 

	INSERT INTO dbo.order_detail
		(quantity, order_id, product_id)
	SELECT d.quantity, a.id, d.product_id
	FROM dbo.orders a 
			JOIN dbo.users b ON b.id = a.user_id 
			JOIN dbo.cart c ON c.id = b.cart_id
			JOIN dbo.cart_product d ON d.cart_id = c.id
	WHERE a.user_id = @userId AND (a.id NOT IN (SELECT order_id FROM dbo.order_detail))

	DELETE FROM dbo.cart_product WHERE cart_id = @cartId
END
GO
/****** Object:  Trigger [dbo].[InsertCartAfterInsertUser]    Script Date: 2022-06-08 9:49:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertCartAfterInsertUser]
ON [dbo].[users]
AFTER INSERT
AS
BEGIN
	DECLARE @id INT
	SELECT @id = id FROM Inserted

    INSERT INTO dbo.cart (createdAt, createdBy, updatedAt, updatedBy)
    VALUES (GETDATE(), NULL, GETDATE(), NULL)

	UPDATE dbo.users
	SET cart_id = (SELECT MAX(id) FROM dbo.cart)
	WHERE id = @id
END
GO
ALTER TABLE [dbo].[users] ENABLE TRIGGER [InsertCartAfterInsertUser]
GO
USE [master]
GO
ALTER DATABASE [ElectronicsShopSpringMVC] SET  READ_WRITE 
GO
