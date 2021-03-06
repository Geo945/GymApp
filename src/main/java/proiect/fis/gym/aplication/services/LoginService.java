package proiect.fis.gym.aplication.services;


import org.dizitart.no2.objects.ObjectRepository;
import proiect.fis.gym.aplication.exceptions.IncorectLoginException;
import proiect.fis.gym.aplication.model.Admin;
import proiect.fis.gym.aplication.model.Customer;
import proiect.fis.gym.aplication.model.GymManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {

    private static ObjectRepository<Customer> customerRepository;
    private static ObjectRepository<Admin> adminRepository;
    private static ObjectRepository<GymManager> managerRepository;

    public static void initDatabase() {

       /* Nitrite database = Nitrite.builder()
               .filePath(getPathToFile("GymAplication.db").toFile())
                .openOrCreate("Geo", "Rares");*/

        customerRepository = CustomerService.getCustomerRepository();//database.getRepository(Customer.class);
        adminRepository = AdminService.getAdminRepository();
        managerRepository = GymManagerService.getGymManagerRepository();
    }

    public static int login(String username,String password) throws IncorectLoginException{
        int ok=0;
        String pw = encodePassword(username,password);
        for (Customer customer : customerRepository.find()) {
            if ( username.equals(customer.getUsername()) && pw.equals(customer.getPassword())  ){
                ok=1;
                break;
            }
        }

        for(Admin admin : adminRepository.find()){
            if(username.equals(admin.getUsername()) && pw.equals(admin.getPassword())){
                ok=2;
                break;
            }
        }

        for(GymManager manager : managerRepository.find()){
            if(username.equals(manager.getUsername()) && pw.equals(manager.getPassword())){
                ok=3;

                break;
            }
        }

        if(ok==1){
            return 1;
        }else if (ok==2){
            return 2;
        }else if(ok==3) {
            return 3;
        }else{
                throw new IncorectLoginException();
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));


        return new String(hashedPassword, StandardCharsets.UTF_8).replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
