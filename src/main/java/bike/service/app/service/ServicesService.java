// package bike.service.app.service;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import bike.service.app.model.Services;
// import bike.service.app.model.repository.ServicesRepository;

// @Service
// public class ServicesService {

//     @Autowired
//     private ServicesRepository servicesRepository;

//     private LocalDate date = LocalDate.now();

//     public List<Services> getAllServices() {
//         System.out.println("getAllServices");
//         List<Services> servicesList = servicesRepository.findAll();
//         if (!servicesList.isEmpty()) {
//             return servicesList;
//         } else {
//             return new ArrayList<>();
//         }
//     }

//     public Services getServicesById(int id) {
//         System.out.println("getServicesById");
//         Optional<Services> optionalServices = servicesRepository.findById(id);

//         if (optionalServices.isPresent()) {
//             return optionalServices.get();
//         } else {
//             throw new RuntimeException("servicesNotFound");
//         }
//     }

//     public void deletedServicesById(int id) {
//         System.out.println("deletedServiceById");
//         Optional<Services> optionalServices = servicesRepository.findById(id);

//         if (optionalServices.isPresent()) {
//             servicesRepository.deleteById(id);
//         } else {
//             throw new RuntimeException("serviceNotDeleted");
//         }
//     }

//     public Services createNewService(String serviceType, Services services, String comment, String deliveryDate) {
//         System.out.println("createUpdateService");
//         if (services.getServiceId() == 0) {
//             switch (serviceType) {
//                 case "smallService":
//                     services.setDate(date.toString());
//                     services.setDeliveryDate(deliveryDate);
//                     services.setComment(comment);
//                     break;
//                     case "fullService":
//                     services.setDate(date.toString());
//                     services.setDeliveryDate(deliveryDate);
//                     services.setComment(comment);
//                     break;
//                     case "otherService":
//                     services.setDate(date.toString());
//                     services.setDeliveryDate(deliveryDate);
//                     services.setComment(comment);
//                     break;
//             }
//             servicesRepository.save(services);
//         }
//         return services;
//     }
//     // it's not neede cos I making "other" classes
//     // public Services createRepairService(Services reprairType, String repairType,
//     // int price) {
//     // System.out.println("createRepairService");
//     // if (reprairType.getServiceId() == 0) {
//     // reprairType.setRepairType(repairType);
//     // reprairType.setRepair(price);
//     // reprairType.setDate(date.toString());
//     // servicesRepository.save(reprairType);
//     // }
//     // return reprairType;
//     // }
// }