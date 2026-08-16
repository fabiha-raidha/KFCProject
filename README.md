# KFC Global Operation
 
A JavaFX desktop application that simulates the internal management system of a KFC franchise, built as an object-oriented programming (OOP) course project. The app models several real operational roles within a fast-food chain — from customer ordering to kitchen, supply, quality, marketing, and store management — each with its own dashboard and workflows.
 
## Overview
 
After logging in, users are routed to a role-specific dashboard based on their credentials:
 
| Role | Username | Password | Dashboard |
|---|---|---|---|
| Head Chef | `chef` | `123` | Kitchen operations |
| CMO (Marketing) | `cmo` | `123` | Marketing & brand |
| Supply | `supply` | `123` | Supply chain |
| Quality | `quality` | `123` | Quality assurance |
| Store Manager | `manager` / `storemanager` | `123` | Store management |
| Customer | `customer` | `123` | Customer dashboard |
| Registered customer | (name/email + password set at signup) | — | Customer dashboard |
 
> **Note:** Staff credentials are hard-coded for demo purposes. Customer accounts are created through the app and authenticated against stored account data.
 
## Features by Module
 
**Head Chef**
- Recipe management
- Inventory management
- Quality control & inspection logs
- Attendance sheet
- Sales reports
- Staff training management
- Kitchen operations
- Waste management

**CMO (Marketing)**
- Brand compliance
- Marketing campaign management
- Customer feedback & sentiment
- Promotional menu management
- Budget allocation

**Supply Chain**
- Supplier management & reports
- Purchase orders
- Inventory overview
- Stock transfers
- Item catalog
- Low-stock alerts
- Receive shipments

**Quality**
- Quality incident tracking
- Store audits
- Training compliance

**Store Manager**
- Sales management
- Staff scheduling & evaluation
- Store expenses
- Maintenance requests
- Inventory requests
- Performance summaries
- Customer feedback

**Customer**
- Browse restaurant menu
- Place food orders online
- Track delivery status
- Leave feedback & ratings
- Create a customer account

## Tech Stack 
- **Java 21**
- **JavaFX 21** (Controls + FXML) for the UI
- **Apache PDFBox 2.0.29** for PDF generation (e.g. reports)
- **Maven** for build and dependency management
- **JUnit 5** for testing
- Custom binary-file–based persistence (Java object serialization) — data is stored in `.bin` files under `data/`, with a shared `Repository` / `BinaryFileRepository` abstraction used across modules
  
## Project Structure
```
KFCProject/
├── data/                          # Serialized .bin data files (recipes, staff, campaigns, etc.)
├── src/
│   ├── main/
│   │   ├── java/class_controller/
│   │   │   ├── Ahnaf_2320401/     # Quality & Supply modules
│   │   │   ├── Fabiha_2211214/    # CMO & Head Chef modules
│   │   │   ├── Marzana_2432038/   # Customer & Store Manager modules
│   │   │   ├── users/             # Authentication (StaffUser, CustomerUser)
│   │   │   ├── Authenticatable.java
│   │   │   ├── Repository.java / BinaryFileRepository.java
│   │   │   ├── HelloApplication.java   # App entry point
│   │   │   └── LogInController.java    # Login logic
│   │   └── resources/class_controller/ # FXML views, per module
│   └── test/
├── pom.xml
└── mvnw / mvnw.cmd                # Maven wrapper
```
 
Each module's Java package pairs a `*Class.java` (data/model class) with a `*Controller.java` (FXML controller), following the MVC pattern, with a matching `.fxml` file under `resources`.
 
## Getting Started
 
### Prerequisites
- JDK 21+
- Maven (or use the included Maven wrapper — no local install required)
### Run the app
 
```bash
# macOS/Linux
./mvnw javafx:run
 
# Windows
mvnw.cmd javafx:run
```
 
This launches the login screen (`LogInView.fxml`). Log in with any of the role credentials above to reach that role's dashboard.
 
### Build
 
```bash
./mvnw clean package
```
 
## Team
 
This project was built collaboratively, with each contributor owning specific modules:
- **Fabiha** — CMO & Head Chef modules
- **Ahnaf** — Quality & Supply modules
- **Marzana** — Customer & Store Manager modules

## License
No license specified. Add one if you intend to share or open-source this project.
 
