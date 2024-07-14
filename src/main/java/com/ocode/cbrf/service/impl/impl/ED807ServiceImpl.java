package com.ocode.cbrf.service.impl.impl;

import com.ocode.cbrf.model.ED807;
import com.ocode.cbrf.repository.ED807Repository;
import com.ocode.cbrf.service.impl.ED807Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ED807ServiceImpl implements ED807Service {
    @Autowired
    private ED807Repository ed807Repository;

    @Override
    public ED807 save(ED807 ed807) {
        return ed807Repository.save(ed807);
    }

    @Override
    public void delete(Long id) {
        Optional<ED807> optionalED807 = ed807Repository.findById(id);
        ED807 ed807= optionalED807.isPresent() ? optionalED807.get() : null;
        if(ed807 != null)
            ed807Repository.delete(ed807);
    }

    @Override
    public List<ED807> getAll() {
        return ed807Repository.findAll();
    }

    @Override
    public List<ED807> getAllBetweenDates(LocalDate startDate, LocalDate endDate) {
        return ed807Repository.findAllBetweenDates(startDate, endDate);
    }

    @Override
    public List<ED807> getAllByAuthor(Long author) {
        return ed807Repository.findAllByAuthor(author);
    }

    @Override
    public List<ED807> getAllByCreationDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ed807Repository.findAllBetweenCreationDateTime(startDateTime, endDateTime);
    }

    @Override
    public List<ED807> getAllByReceiver(Long receiver) {
        return ed807Repository.findAllByReceiver(receiver);
    }

    @Override
    public Optional<ED807> getByNumber(Integer number) {
        return ed807Repository.findByNumber(number);
    }
}
