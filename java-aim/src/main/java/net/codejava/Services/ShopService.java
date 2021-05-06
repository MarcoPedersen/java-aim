package net.codejava.Services;

import java.util.List;


import net.codejava.Models.Shop;
import net.codejava.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShopService {

    @Autowired
    private ShopRepository repo;

    public List<Shop> listAll() {
        return repo.findAll();
    }

    public void save(Shop shop) {
        repo.save(shop);
    }

    public Shop get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
