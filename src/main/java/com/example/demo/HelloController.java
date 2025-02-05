package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HelloController {
    @FXML
    private ListView<String> listView;
    @FXML
    private PieChart pieChart;

    private List<Country> orszagok = new ArrayList<>();
    @FXML
    void beOlvas(ActionEvent event) throws FileNotFoundException {
        File file = new File("varosokDatas.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split(",");
            orszagok.add(new Country(split[0], Integer.parseInt(split[1])));
        }

        for (Country country : orszagok) {
            listView.getItems().add(country.toString());
        }

    }

    @FXML
    void csokkenoSor(ActionEvent event) {
        listView.getItems().clear();
        orszagok.reversed();
        System.out.println(orszagok);
        for (Country country : orszagok) {
            listView.getItems().add(country.toString());
        }

    }

    @FXML
    void loadPieChart(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Country country : orszagok) {
            pieChartData.add(new PieChart.Data(country.getName(), country.getPopulation()));
        }

        pieChart.setData(pieChartData);
        pieChart.setTitle("Népesség");
    }

    @FXML
    void novekvoSorr(ActionEvent event) {
        listView.getItems().clear();
        orszagok.sort(Comparator.comparing(Country::getPopulation));
        System.out.println(orszagok);
        for (Country country : orszagok) {
            listView.getItems().add(country.toString());
        }
    }

}
