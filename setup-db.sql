-- Create database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'adphilip_db')
BEGIN
    CREATE DATABASE adphilip_db;
END
GO

USE adphilip_db;
GO

-- Create login
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = 'adphilip')
BEGIN
    CREATE LOGIN adphilip WITH PASSWORD = 'Str0ngP@ssw0rd!#2025';
END
GO

-- Create user
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'adphilip')
BEGIN
    CREATE USER adphilip FOR LOGIN adphilip;
END
GO

-- Grant permissions
ALTER ROLE db_owner ADD MEMBER adphilip;
GO

PRINT 'Database setup completed successfully!';
GO
