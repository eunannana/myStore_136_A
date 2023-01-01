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

    @RequestMapping("/getProduct/{id}")
    public String getData(@PathVariable("id") int id) {

        try {
            data = dataCtrl.findMyproduct(id);
            return data.getProductName() + "<br>" + data.getQty();
        } catch (Exception error) {
            return "Product is not found!!!";
        }
    }

    @RequestMapping("/getProductAll")
    public List<Myproduct> getDataAll() {
        return dataCtrl.findMyproductEntities();
    }

    @RequestMapping("/addProduct")
    public String addData(@RequestBody Myproduct theproduct) {
        try {
            dataCtrl.create(theproduct);
            return "Successfully added product";
        } catch (Exception message) {
            return "Product is already exist";
        }
    }

    @RequestMapping("/editProduct/{id}")
    public String editData(@PathVariable("id") int id, @RequestBody Myproduct theproduct) {
        try {
            data = dataCtrl.findMyproduct(id);
            theproduct.setProductID(id);

            if (theproduct.getProductID() != id) {
                return "Product doesn't exist!";
            } else if (theproduct.getProductName() == null) {
                data = dataCtrl.findMyproduct(id);
                theproduct.setProductName(data.getProductName());
                dataCtrl.edit(theproduct);
                Myproduct newproduct = new Myproduct();
                newproduct = dataCtrl.findMyproduct(id);
                return "Successfully edited :"
                        + "\n - ProductID : " + data.getProductID()
                        + "\n - ProductName : " + newproduct.getProductName()
                        + "\n - Qty : " + newproduct.getQty();
            } else if (theproduct.getQty() == null) {
                data = dataCtrl.findMyproduct(id);
                theproduct.setQty(data.getQty());
                dataCtrl.edit(theproduct);
                Myproduct newproduct = new Myproduct();
                newproduct = dataCtrl.findMyproduct(id);
                return "Successfully edited : "
                        + "\n - ProductID : " + data.getProductID()
                        + "\n - ProductName : " + newproduct.getProductName()
                        + "\n - Qty : " + newproduct.getQty();
            } else if (theproduct.getProductName() != null && theproduct.getQty() != null) {
                dataCtrl.edit(theproduct);
                Myproduct newproduct = new Myproduct();
                newproduct = dataCtrl.findMyproduct(id);
                return "Successfully edited : "
                        + "\n - ProductID : " + data.getProductID()
                        + "\n - ProductName : " + newproduct.getProductName()
                        + "\n - Qty : " + newproduct.getQty();
            } else {
                return "ERROR!";
            }
        } catch (Exception e) {
            return "Product is not found!";
        }
    }

    @RequestMapping("/deleteData/{id}")
    public String deleteData(@PathVariable("id") int id) {
        try {
            dataCtrl.destroy(id);
            return "Successfully deleted product";
        } catch (NonexistentEntityException error) {
            return "Product is not found!!!";
        }
    }
}
