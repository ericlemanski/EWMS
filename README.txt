# WMS Project - Manual Setup

## 📦 Requirements
- Java JDK (17 or above recommended)
- PostgreSQL running with a table called "loc"
- Internet to download .jar files

## 🔧 Setup

1. Place these .jar files into backend/lib/:
   - spark-core-2.9.3.jar
   - gson-2.10.jar
   - postgresql-42.6.0.jar

2. Open terminal in backend/ folder.

3. Compile:
   javac -cp "lib/*" WMSBackend.java

4. Run:
   java -cp ".:lib/*" WMSBackend
   (On Windows: use `.;lib/*` instead)

5. Open frontend/index.html in browser to view the UI.
