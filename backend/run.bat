@echo off
echo Compiling WMSBackend.java...
javac -cp "lib/*" WMSBackend.java

if %ERRORLEVEL% neq 0 (
    echo Compile failed.
    pause
    exit /b
)

echo Running WMSBackend...
java -cp ".;lib/*" WMSBackend
pause
