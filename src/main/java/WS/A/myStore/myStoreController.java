/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS.A.myStore;

import WS.A.myStore.exceptions.NonexistentEntityException;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eunannana
 */
@RestController
public class myStoreController {

    Myproduct data = new Myproduct();
    MyproductJpaController dataCtrl = new MyproductJpaController();

     @RequestMapping("/getProductName/{id}")
    public String getProductName(@PathVariable("id") int id){

        try{
            data = dataCtrl.findMyproduct(id);
            return data.getProductName()+"<br>"+ data.getQty();
        }
        catch (Exception error){
            return "Data is not found!!!";
        }
    }

    @RequestMapping("/getProductAll")
    public List<Myproduct> getAll()
    {
        return dataCtrl.findMyproductEntities();
    }

    @RequestMapping("/AddProduct")
    public String addData(@RequestBody Myproduct productID) {
        try{
            dataCtrl.create(productID);
            return "Successfully added data";
        }
        catch(Exception message){
            return "Product is already exist";
        }
    }

    @RequestMapping("/delete/{id}")
    public String deleteData(@PathVariable("id") int id){
        try{
            dataCtrl.destroy(id);
            return id + " successfully deleted";
        }
        catch(NonexistentEntityException error){
            return id + " Data is not found!!!";
        }
    }

}