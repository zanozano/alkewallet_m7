# AlkeWallet

AlkeWallet is a virtual wallet that allows managing funds, transferring between accounts, and performing currency exchanges. This project is developed using Spring Boot and offers basic functionalities of an electronic wallet.

## Features

- User registration
- Login
- Viewing accounts and balances
- Deposits and withdrawals
- Transfers between accounts
- Currency exchange
- Logout

## Project Structure

### Controllers

- **AccountController**: Manages operations related to user accounts, including viewing balances, transactions, deposits, withdrawals, currency exchanges, and logout.
- **HomeController**: Displays the main homepage.
- **LoginController**: Handles user login.
- **SignupController**: Manages user registration.

### DTOs (Data Transfer Objects)

- **TransactionRequest**: Represents a transaction request.
- **TransactionResponse**: Represents a transaction response.

### Models

- **User**: Represents a user of the application.

### Services

- **UserService**: Manages user-related operations, including authentication, fetching users by email, user registration, and fetching user emails by their IDs.

## Requirements

- Java 11+
- Spring Boot
- Maven

## Installation

1. Clone the repository:

```bash
git clone https://github.com/your-repository/alkewallet.git
```

2. Navigate to the project directory:

```bash
cd alkewallet
```

3. Compile the project:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

## Usage

1. Open your web browser and go to `http://localhost:8080`.
2. Register or log in to access your account.
3. Use the wallet functionalities from the web interface.