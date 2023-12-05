# Walmart
E-Commerce API Like [walmart.com](https://www.walmart.com) with spring boot

[![Under development](https://img.shields.io/badge/under_development-blue.svg)](https://github.com/sameh-tarek/Walmart)

# Test the API here :

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/28660393-ec8e2ab9-4dc3-4543-a350-7f3df3a051fb?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D28660393-ec8e2ab9-4dc3-4543-a350-7f3df3a051fb%26entityType%3Dcollection%26workspaceId%3D283cc664-cb53-491a-baad-e2e698e43b37)


# How to Run

1- Clone the project repository from Git (if it's not already cloned).

2- Import the project into your favorite Java IDE (e.g., IntelliJ, Eclipse, etc.).

3- create public key and private key in a new folder under /src/main/resources/certs 

run this commands in terminal
```code
# create rsa key pair
openssl genrsa -out keypair.pem 2048
# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem
# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

4- to send Email Verification you should add your Email and Password in application.yml file

4- Build the project to resolve dependencies.

# Details about the project

This project is designed to showcase a simplified online shopping experience with basic admin tools.

## User Features:

### Account:
- **Sign Up:**
  - Create a new account easily.
- **Log In:**
  - Log in with your username and password.

### Shopping:
- **Browse and Buy:**
  - Explore products and add them to your cart.
  - Complete your purchase with a simple checkout.

### User Profile:
- **Personalization:**
  - Edit your profile details.

### Admin Features:

### Product Management:
- **Manage Products:**
  - Add, edit, or remove products easily.

### Order Management:
- **View Orders:**
  - See a list of customer orders.

### User Management:
- **Manage Users:**
  - View user accounts.

### Inventory:
- **Stock Monitoring:**
  - Keep track of product stock levels.

### Basic Analytics:
- **Sales Reports:**
  - View basic sales reports.

## Expected Features (Initial Release):

The initial release of the Walmart Ecommerce Project includes:
- **User Registration and Authentication:**
  - Allow users to sign up and log in.
- **Product Browsing and Shopping Cart:**
  - Let users explore products and make purchases.
- **User Profile Management:**
  - Enable users to edit their profile details.
- **Basic Admin Features:**
  - Allow admin to manage products, view orders, manage users, monitor inventory, and access basic sales reports.

