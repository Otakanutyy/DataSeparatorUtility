# DataSeparatorUtility

A tiny CLI utility built with Spring Boot 3 and Java 17.  
Scans text files, splits lines into **integers**, **floats**, and **strings**, writes each type to its own file, and prints simple (`-s`) or full (`-f`) statistics. Supports appending (`-a`) and custom output dir / filename prefix (`-o`, `-p`). Handles missing files and I/O errors without crashing.

---

## 🚀 Quick Start

### 1. **Clone or Download This Repository**
```bash
git clone https://github.com/your-user/DataSeparatorUtility
cd DataSeparatorUtility/Utility
```

### 2. **Build the Project**
```bash
mvn clean package      # Maven 3.9+  /  JDK 17
```

### 3. **Run the Utility — Linux / macOS**
```bash
java -jar target/Utility-0.0.1-SNAPSHOT.jar -f -p run1- -o results input_txt/*.txt
```

### 4. **Run the Utility — Windows PowerShell**
```bash
java -jar target\Utility-0.0.1-SNAPSHOT.jar -f -o results (Get-ChildItem input_txt\*.txt).FullName
```

### 5. **Append Instead of Overwrite**
```bash
java -jar target/Utility-0.0.1-SNAPSHOT.jar -a input_txt/*.txt
```

## ⚙️ CLI Flags
| Flag | Description |
|------|-------------|
| `-s` | Simple stats (counts only) |
| `-f` | Full stats (min / max / sum / avg for numbers; shortest / longest for strings) |
| `-a` | Append to existing result files |
| `-o <dir>` | Output directory for result files |
| `-p <prefix>` | Prefix for filenames (`<prefix>integers.txt`, …) |
| `<files>` | One or more text-file paths to process |

## 🗂️ Example Output Structure
results/
├─ run1-integers.txt
├─ run1-floats.txt
└─ run1-strings.txt

## 📦 Requirements & Dependencies

| Tool / Library | Version | Link |
|----------------|---------|------|
| Java SE | **17** | https://adoptium.net/ |
| Maven | **3.9+** | https://maven.apache.org/ |
| Spring Boot Starter | **3.3.0** | https://spring.io/projects/spring-boot |
| Apache Commons Lang | **3.14.0** | https://commons.apache.org/proper/commons-lang/ |
| JUnit Jupiter (test only) | **5.10.0** | https://junit.org/junit5/ |

### All dependencies are resolved automatically via Maven._

## 📝 Implementation Notes
* Buffered, single-pass processing; writers open lazily per data type.
* `BigInteger` / `BigDecimal` prevent numeric overflow.
* On read / write failure logs an error and continues with the next line or file.
* No persistent state; stats live only during one run.
