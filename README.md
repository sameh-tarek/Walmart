# Walmart
E-Commerce API Like [walmart.com](https://www.walmart.com) with spring boot

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
