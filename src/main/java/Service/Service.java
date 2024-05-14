package Service;

import Domain.Product;
import Repository.AbstractRepository;

import java.util.ArrayList;


public class Service {
    AbstractRepository<Product> productRepository;


    public Service(AbstractRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product entity) {
        productRepository.add(entity);
    }


    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }

    public void updateProduct(Integer id, Product entity) {
        productRepository.update(id, entity);
    }


    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(productRepository.getAll());
    }


//    public List<Object[]> getMostRentedCars() {
//
//        List<Car> cars = getAllCars();
//        List<Rent> rents = getAllRents();
//
//        Map<Integer, Long> rentsCountByCar = rents.stream()
//                .collect(Collectors.groupingBy(Rent::getIdCar, Collectors.counting()));
//
//
//        List<Object[]> result = cars.stream()
//                .map(car -> new Object[]{car, rentsCountByCar.getOrDefault(car.getId(), 0L)})
//                .sorted((a, b) -> Integer.compare((Integer) b[1], (Integer) a[1]))
//                .collect(Collectors.toList());
//        return result;
//    }
//
//    public Map<String, Long> nrOfRentsPerMonthInYear(Integer year) {
//
//        List<Rent> rents = getAllRents();
//
//        Map<String, Long> rentsPerMonth = rents.stream()
//                .filter(rent -> {
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(rent.getStartDate());
//                    return cal.get(Calendar.YEAR) == year;
//                })
//                .collect(Collectors.groupingBy(
//                        rent -> {
//                            Calendar cal = Calendar.getInstance();
//                            cal.setTime(rent.getStartDate());
//                            // Use month name as the key
//                            return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
//                        },
//                        Collectors.counting()
//                ));
//
//
//        Map<String, Long> sortedRentsPerMonth = rentsPerMonth.entrySet().stream()
//                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (e1, e2) -> e1, LinkedHashMap::new));
//
//        return sortedRentsPerMonth;
//    }
//
//
//    public Map<Car, Long> longestRents(){
//
//        List<Rent>rents=getAllRents();
//        List<Car>cars=getAllCars();
//
//        Map<Car, Long> totalDaysRented = cars.stream()
//                .collect(toMap(
//                        car -> car,
//                        car -> rents.stream()
//                                .filter(rent -> rent.getIdCar().equals(car.getId()))
//                                .mapToLong(rent -> ChronoUnit.DAYS.between((Temporal) rent.getStartDate(), (Temporal) rent.getEndDate()))
//                                .sum())
//                );
//
//        // Sort the map by total days rented in descending order
//        Map<Car, Long> sortedMap = totalDaysRented.entrySet().stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//
//       return sortedMap;
//    }


}
