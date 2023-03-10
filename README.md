# 🚘 Auto-service-app ⚙️
___

## 📝 Description
___
    This is a damonstration of accounting application for autoservice. It supports adding autoservice clients,
    their cars, creating orders with services and spare parts. Also, there is an opporunity to calculate order
    price for each owner. Mechanics salary is calculated according to their completed orders too. In addition 
    there is a discounts system based on client`s orders quantity.

## 💻 Technologies
___
| **Technology** | **Version** |
|----------------|-------------|
| JDK            | 17          |
| Maven          | 4.0.0       |
| Spring Boot    | 2.5.9       |
| Postgres       | 15          |
| Swagger UI     | 2.7.0       |
| Liquibase      | -           |

* Implemented following N-tire architecture and SOLID principles

## ⚙️Set up and run an application locally
___
1. Clone this project
2. Install PostgreSQL and set properties in [application.properties](https://github.com/luka-lozovyk/auto-service-app/blob/662e4083ea6a1746b2e18a45168a04b4d08c8081/src/main/resources/application.properties#L1)
3. Run application
4. Follow [this link](http://localhost:8080/swagger-ui.html#/) to test features in Swagger 