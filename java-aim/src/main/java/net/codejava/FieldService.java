package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FieldService {

    @Autowired
    private FieldRepository repo;

    public List<FieldModel> listAll() {
        return repo.findAll();
    }


    public void save(FieldModel fieldModel) {
        repo.save(fieldModel);
    }

    public FieldModel get(Integer id) { return repo.findById(id).get(); }

    public void delete(Integer id) { repo.deleteById(id); }
}
