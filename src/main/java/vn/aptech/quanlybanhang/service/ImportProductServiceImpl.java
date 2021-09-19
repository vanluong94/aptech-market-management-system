/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.util.List;
import vn.aptech.quanlybanhang.dao.ImportProductDAO;
import vn.aptech.quanlybanhang.dao.ImportProductDAOImpl;
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ImportProductServiceImpl implements ImportProductService {

    private final ImportProductDAO importProductDAO;

    public ImportProductServiceImpl() {
        this.importProductDAO = new ImportProductDAOImpl();
    }

    @Override
    public boolean create(ImportProduct object) throws Exception {
        return importProductDAO.create(object);
    }

    @Override
    public boolean update(ImportProduct object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImportProduct findById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImportProduct> findAll() throws Exception {
        return importProductDAO.findAll();
    }

    @Override
    public PaginatedResults<ImportProduct> select(int page) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
