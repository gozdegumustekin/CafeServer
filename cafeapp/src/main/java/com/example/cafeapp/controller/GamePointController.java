package com.example.cafeapp.controller;

import com.example.cafeapp.entity.GamePoint;
import com.example.cafeapp.repository.GamePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/points")
public class GamePointController {

    @Autowired
    private GamePointRepository gamePointRepository;

    // 🔹 Tüm puanları listele
    @GetMapping
    public List<GamePoint> getAllPoints() {
        return gamePointRepository.findAll();
    }

    // 🔹 ID'ye göre puan getir
    @GetMapping("/{id}")
    public Optional<GamePoint> getPointById(@PathVariable Long id) {
        return gamePointRepository.findById(id);
    }

    // 🔹 Yeni puan ekle
    @PostMapping
    public GamePoint createPoint(@RequestBody GamePoint gamePoint) {
        return gamePointRepository.save(gamePoint);
    }

    // 🔹 Oyuncuya göre puanları filtrele
    @GetMapping("/player/{playerName}")
    public List<GamePoint> getPointsByPlayerName(@PathVariable String playerName) {
        return gamePointRepository.findAll().stream()
                .filter(p -> p.getPlayerName().equalsIgnoreCase(playerName))
                .toList();
    }

    // 🔹 Puan güncelle
    @PutMapping("/{id}")
    public GamePoint updatePoint(@PathVariable Long id, @RequestBody GamePoint newPoint) {
        return gamePointRepository.findById(id).map(point -> {
            point.setPlayerName(newPoint.getPlayerName());
            point.setScore(newPoint.getScore());
            return gamePointRepository.save(point);
        }).orElseThrow(() -> new RuntimeException("Point not found with id " + id));
    }

    // 🔹 Puan sil
    @DeleteMapping("/{id}")
    public void deletePoint(@PathVariable Long id) {
        gamePointRepository.deleteById(id);
    }
}
