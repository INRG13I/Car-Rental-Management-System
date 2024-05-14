//package User_Interface;
//
//
//import Domain.Car;
//import Domain.Rent;
//import Service.Service;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//public class ConsoleInterface {
//
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//    private Service service;
//
//    private Scanner input = new Scanner(System.in);
//
//    public ConsoleInterface(Service service) {
//        this.service = service;
//    }
//
//    private void printMenu() {
//        System.out.print("""
//                1. Add car\s
//                2. Add rent
//                3. Delete car
//                4. Delete rent
//                5. Update car
//                6. Update rent
//                7. Show cars
//                8. Show rents
//                9. Get most rented cars
//                10. Nr. of rents per month in year
//                11. LongestRents
//                12. Exit
//                \s""");
//    }
//
//    private void addCar() {
//        Integer id;
//        String make;
//        String model;
//
//        System.out.println("Enter car id: ");
//        id = input.nextInt();
//        input.nextLine();
//        System.out.println("Enter car make: ");
//        make = input.nextLine();
//        System.out.println("Enter car model: ");
//        model = input.nextLine();
//
//        service.addProduct(new Car(id, make, model));
//
//    }
//
//    private void addRent() {
//
//        Integer id;
//        Integer carId;
//        java.sql.Date startDate;
//        java.sql.Date endDate;
//
//        System.out.println("Enter id: ");
//        id = input.nextInt();
//        input.nextLine();
//        System.out.println("Enter car id: ");
//        carId = input.nextInt();
//        input.nextLine();
//
//        try {
//            System.out.println("Enter start date (yyyy-MM-dd): ");
//            startDate =new java.sql.Date(simpleDateFormat.parse(input.nextLine()).getTime());
//            System.out.println("Enter end date (yyyy-MM-dd): ");
//            endDate =new java.sql.Date(simpleDateFormat.parse(input.nextLine()).getTime());
//            service.addRent(new Rent(id, carId, startDate, endDate));
//        } catch (ParseException e) {
//            addRent();
//        }
//
//
//    }
//
//    private void deleteCar() {
//        Integer id;
//
//        System.out.println("Enter car id: ");
//        id = input.nextInt();
//        input.nextLine();
//
//        service.deleteCar(id);
//
//    }
//
//    private void deleteRent() {
//        Integer id;
//
//        System.out.println("Enter rent id: ");
//        id = input.nextInt();
//        input.nextLine();
//
//        service.deleteRent(id);
//    }
//
//    private void updateCar() {
//        Integer idToUpdate;
//        Integer id;
//        String make;
//        String model;
//        System.out.println("Enter id of car to update: ");
//        idToUpdate = input.nextInt();
//        input.nextLine();
//        System.out.println("Enter new car id: ");
//        id = input.nextInt();
//        input.nextLine();
//        System.out.println("Enter new car make: ");
//        make = input.nextLine();
//        System.out.println("Enter new car model: ");
//        model = input.nextLine();
//
//        service.updateCar(idToUpdate, new Car(id, make, model));
//    }
//
//    private void updateRent() {
//
//        Integer idToReplace;
//        Integer id;
//        Integer carId;
//        java.sql.Date startDate;
//        java.sql.Date endDate;
//
//
//        System.out.println("Enter id of updated rent: ");
//        idToReplace = input.nextInt();
//        System.out.println("Enter new id: ");
//        id = input.nextInt();
//        input.nextLine();
//        System.out.println("Enter new car id: ");
//        carId = input.nextInt();
//        input.nextLine();
//
//        try {
//            System.out.println("Enter new start date (yyyy-MM-dd): ");
//            startDate =new java.sql.Date(simpleDateFormat.parse(input.nextLine()).getTime());
//            System.out.println("Enter new end date (yyyy-MM-dd): ");
//            endDate =new java.sql.Date(simpleDateFormat.parse(input.nextLine()).getTime());
//            service.updateRent(idToReplace, new Rent(id, carId, startDate, endDate));
//        } catch (ParseException e) {
//            addRent();
//        }
//
//    }
//
//    private void showAllCars() {
//        for (Car car : service.getAllCars()) {
//            System.out.println(car.toString());
//        }
//    }
//
//    private void showAllRents() {
//        for (Rent rent : service.getAllRents()) {
//            System.out.println(rent.toString());
//        }
//    }
//
//    private void getMostRentedCars() {
//        List<Object[]> mostRentedCars = service.getMostRentedCars();
//        for (Object[] carInfo : mostRentedCars) {
//            Car car = (Car) carInfo[0];
//            Long rentCount = (Long) carInfo[1];
//
//            String rentCountMessage;
//            if (rentCount > 0) {
//                rentCountMessage = "This car has been rented.";
//            } else {
//                rentCountMessage = "This car has not been rented.";
//            }
//
//            System.out.println(car + " - " + rentCountMessage);
//        }
//
//    }
//
//    private void nrOfRentsPerMonthInYear() {
//        Integer year;
//        System.out.println("Input year:");
//        year = input.nextInt();
//        input.nextLine();
//
//        Map<String, Long> rentsPerMonth = service.nrOfRentsPerMonthInYear(year);
//
//        for (Map.Entry<String, Long> entry : rentsPerMonth.entrySet()) {
//            String month = entry.getKey();
//            long rentCount = entry.getValue();
//
//            System.out.println(month + ": " + rentCount + " rents");
//        }
//    }
//
//    private void longestRents(){
//        Map<Car,Long> result=service.longestRents();
//        for (Map.Entry<Car, Long> entry : result.entrySet()) {
//            Car car = entry.getKey();
//            String carInfo = "Make: " + car.getMake() + ", Model: " + car.getModel();
//
//            long totalDaysRented = entry.getValue();
//            String durationMessage;
//            if (totalDaysRented > 0) {
//                durationMessage = "This car has been rented for a total of " + totalDaysRented + " days.";
//            } else {
//                durationMessage = "This car has not been rented.";
//            }
//
//            System.out.println(carInfo + " - " + durationMessage);
//        }
//
//    }
//
//    public void run() {
//        Integer option;
//
//        while (true) {
//
//            printMenu();
//
//            System.out.println("Choose an option: ");
//
//            option = input.nextInt();
//            input.nextLine();
//
//            switch (option) {
//                case 1:
//                    addCar();
//                    break;
//                case 2:
//                    addRent();
//                    break;
//                case 3:
//                    deleteCar();
//                    break;
//                case 4:
//                    deleteRent();
//                    break;
//                case 5:
//                    updateCar();
//                    break;
//                case 6:
//                    updateRent();
//                    break;
//                case 7:
//                    showAllCars();
//                    break;
//                case 8:
//                    showAllRents();
//                    break;
//                case 9:
//                    getMostRentedCars();
//                    break;
//                case 10:
//                    nrOfRentsPerMonthInYear();
//                    break;
//                case 11:
//                    longestRents();
//                    break;
//                case 12:
//                    return;
//            }
//        }
//    }
//}
