@echo off
echo [INFO] Checking for running Java backend processes...
for /f "tokens=2" %%i in ('jps ^| find "WMSBackend"') do taskkill /PID %%i /F

echo [INFO] Cleaning previous builds...
del /Q backend\bin\*.class

echo [INFO] Recompiling Java backend...
set CLASSPATH=backend\lib\spark-core-2.9.4.jar;backend\lib\gson-2.10.1.jar
javac -cp %CLASSPATH% -d backend\bin backend\src\WMSBackend.java

if %ERRORLEVEL% neq 0 (
    echo [ERROR] Compilation failed.
    exit /b %ERRORLEVEL%
)

echo [INFO] Starting Java backend...
start cmd /k java -cp "backend\bin;%CLASSPATH%" WMSBackend
