USE [NutriDB_Relational]
GO
/****** Object:  Table [dbo].[unidadesReferencia]    Script Date: 13/12/2016 12:40:11 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UnidadesEmGramas](
	[unidade] [varchar](50) NOT NULL,
	[gramas] [int] NULL,
 CONSTRAINT [PK_UnidadesEmGramas] PRIMARY KEY CLUSTERED 
(
	[unidade] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'chávenas', 150)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'colheres de café', 3)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'colheres de chá', 5)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'colheres de sobremesa', 10)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'colheres de sopa', 15)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'copos', 200)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'cubos', 15)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'dentes', 20)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'fios', 10)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'folhas', 15)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'g', 1)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'kg', 1000)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'l', 1000)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'litro', 1000)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'ml', 1)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'pacotes', 150)
INSERT [dbo].[UnidadesEmGramas] ([unidade], [gramas]) VALUES (N'postas', 125)
