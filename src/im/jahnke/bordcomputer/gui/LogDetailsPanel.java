package im.jahnke.bordcomputer.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import im.jahnke.bordcomputer.Route;

public class LogDetailsPanel extends JPanel {

	private static final long serialVersionUID = -7921863306167811760L;
	
	JPanel overviewPanel;
	JPanel timePanel;
	JPanel speedPanel;
	JPanel heightPanel;
	JPanel fuelPanel;
	JPanel temperaturePanel;
	
	JLabel pointsLabel;
	JLabel distanceLabel;
	JLabel areaLabel;
	JLabel pointsValue;
	JLabel distanceValue;
	JLabel areaValue;	
	JLabel elapsedTimeLabel;
	JLabel drivingTimeLabel;
	JLabel standStillTimeLabel;	
	JLabel elapsedTimeValue;
	JLabel drivingTimeValue;
	JLabel standStillValue;	
	JLabel averageSpeedLabel;
	JLabel averageDrivingSpeedLabel;
	JLabel minSpeedLabel;
	JLabel maxSpeedLabel;
	JLabel averageSpeedValue;
	JLabel averageDrivingSpeedValue;
	JLabel minSpeedValue;
	JLabel maxSpeedValue;	
	JLabel minHeightLabel;
	JLabel maxHeightLabel;
	JLabel elevationUpHillLabel;
	JLabel elevationDownHillLabel;
	JLabel minHeightValue;
	JLabel maxHeightValue;
	JLabel elevationUpHillValue;
	JLabel elevationDownHillValue;	
	JLabel averageFuelLabel;
	JLabel minFuelLabel;
	JLabel maxFuelLabel;
	JLabel fuelVolumeLabel;
	JLabel averageFuelValue;
	JLabel minFuelValue;
	JLabel maxFuelValue;
	JLabel fuelVolumeValue;
	
	JLabel averageOuterTemperatureLabel;
	JLabel minOuterTemperatureLabel;
	JLabel maxOuterTemperatureLabel;
	JLabel averageOuterTemperatureValue;
	JLabel minOuterTemperatureValue;
	JLabel maxOuterTemperatureValue;
	
	

	public LogDetailsPanel() {
		GridLayout layout = new GridLayout(2, 3);
		setLayout(layout);
		
		overviewPanel = new JPanel();
		overviewPanel.setBorder(BorderFactory.createTitledBorder("Übersicht"));
		overviewPanel.setLayout(new GridLayout(4,2));
		add(overviewPanel);
		
		timePanel = new JPanel();
		timePanel.setBorder(BorderFactory.createTitledBorder("Zeit"));
		timePanel.setLayout(new GridLayout(4,2));
		add(timePanel);
				
		speedPanel = new JPanel();
		speedPanel.setBorder(BorderFactory.createTitledBorder("Geschwindigkeit"));
		speedPanel.setLayout(new GridLayout(4,2));
		add(speedPanel);		
		
		heightPanel = new JPanel();
		heightPanel.setBorder(BorderFactory.createTitledBorder("Höhe"));
		heightPanel.setLayout(new GridLayout(4,2));
		add(heightPanel);
		
		fuelPanel = new JPanel();
		fuelPanel.setBorder(BorderFactory.createTitledBorder("Sprit"));
		fuelPanel.setLayout(new GridLayout(4,2));
		add(fuelPanel);
		
		temperaturePanel = new JPanel();
		temperaturePanel.setBorder(BorderFactory.createTitledBorder("Temperatur"));
		temperaturePanel.setLayout(new GridLayout(4,2));
		add(temperaturePanel);

		addLabels();
	}
	
	private void addLabels(){
		pointsLabel = createTextLabel(pointsLabel, "Punkte:");
		distanceLabel = createTextLabel(distanceLabel, "Distanz:");
		areaLabel = createTextLabel(areaLabel, "Fläche:");
		pointsValue = createValueLabel(pointsValue);
		distanceValue = createValueLabel(distanceValue);
		areaValue = createValueLabel(areaValue);
		
		overviewPanel.add(pointsLabel);
		overviewPanel.add(pointsValue);
		overviewPanel.add(distanceLabel);
		overviewPanel.add(distanceValue);
		overviewPanel.add(areaLabel);
		overviewPanel.add(areaValue);
		
		elapsedTimeLabel = createTextLabel(elapsedTimeLabel, "Verstrichene Zeit:");
		drivingTimeLabel = createTextLabel(drivingTimeLabel, "Zeit in Fahrt:");
		standStillTimeLabel = createTextLabel(standStillTimeLabel, "Pause:");		
		elapsedTimeValue = createValueLabel(elapsedTimeValue);
		drivingTimeValue = createValueLabel(drivingTimeValue);
		standStillValue = createValueLabel(standStillValue);
		
		timePanel.add(elapsedTimeLabel);
		timePanel.add(elapsedTimeValue);
		timePanel.add(drivingTimeLabel);
		timePanel.add(drivingTimeValue);
		timePanel.add(standStillTimeLabel);
		timePanel.add(standStillValue);
		
		averageSpeedLabel = createTextLabel(averageSpeedLabel, "Durchschnitt:");
		averageDrivingSpeedLabel = createTextLabel(averageDrivingSpeedLabel, "\u00D8 in Fahrt:");
		minSpeedLabel = createTextLabel(minSpeedLabel, "Min:");
		maxSpeedLabel = createTextLabel(maxSpeedLabel, "Max:");
		averageSpeedValue = createValueLabel(averageSpeedValue);
		averageDrivingSpeedValue = createValueLabel(averageDrivingSpeedValue);
		minSpeedValue = createValueLabel(minSpeedValue);
		maxSpeedValue = createValueLabel(maxSpeedValue);
		
		speedPanel.add(averageSpeedLabel);
		speedPanel.add(averageSpeedValue);
		speedPanel.add(averageDrivingSpeedLabel);
		speedPanel.add(averageDrivingSpeedValue);
		speedPanel.add(minSpeedLabel);
		speedPanel.add(minSpeedValue);
		speedPanel.add(maxSpeedLabel);
		speedPanel.add(maxSpeedValue);
		
		minHeightLabel = createTextLabel(minHeightLabel, "Min:");
		maxHeightLabel = createTextLabel(maxHeightLabel, "Max:");
		elevationUpHillLabel = createTextLabel(elevationUpHillLabel, "Anstieg:");
		elevationDownHillLabel = createTextLabel(elevationDownHillLabel, "Abstieg:");
		minHeightValue = createValueLabel(minHeightValue);
		maxHeightValue = createValueLabel(maxHeightValue);
		elevationUpHillValue = createValueLabel(elevationUpHillValue);
		elevationDownHillValue = createValueLabel(elevationDownHillValue);
		
		heightPanel.add(minHeightLabel);
		heightPanel.add(minHeightValue);
		heightPanel.add(maxHeightLabel);
		heightPanel.add(maxHeightValue);
		heightPanel.add(elevationUpHillLabel);
		heightPanel.add(elevationUpHillValue);
		heightPanel.add(elevationDownHillLabel);
		heightPanel.add(elevationDownHillValue);
		
		averageFuelLabel = createTextLabel(averageFuelLabel, "\u00D8 Verbrauch:");
		minFuelLabel = createTextLabel(minFuelLabel, "Min. Verbrauch:");
		maxFuelLabel = createTextLabel(maxFuelLabel, "Max. Verbrauch:");
		fuelVolumeLabel = createTextLabel(fuelVolumeLabel, "Gesamtverbrauch:");
		averageFuelValue = createValueLabel(averageFuelValue);
		minFuelValue = createValueLabel(minFuelValue);
		maxFuelValue = createValueLabel(maxFuelValue);
		fuelVolumeValue = createValueLabel(fuelVolumeValue);
		
		fuelPanel.add(averageFuelLabel);
		fuelPanel.add(averageFuelValue);
		fuelPanel.add(minFuelLabel);
		fuelPanel.add(minFuelValue);
		fuelPanel.add(maxFuelLabel);
		fuelPanel.add(maxFuelValue);
		fuelPanel.add(fuelVolumeLabel);
		fuelPanel.add(fuelVolumeValue);
		
		averageOuterTemperatureLabel = createTextLabel(averageOuterTemperatureLabel, "\u00D8 Temperatur:");
		minOuterTemperatureLabel = createTextLabel(minOuterTemperatureLabel, "Min. Temperatur:");
		maxOuterTemperatureLabel = createTextLabel(maxOuterTemperatureLabel, "Max. Temperatur:");
		averageOuterTemperatureValue = createValueLabel(averageOuterTemperatureValue);
		minOuterTemperatureValue = createValueLabel(minOuterTemperatureValue);
		maxOuterTemperatureValue = createValueLabel(maxOuterTemperatureValue);
		
		temperaturePanel.add(averageOuterTemperatureLabel);
		temperaturePanel.add(averageOuterTemperatureValue);
		temperaturePanel.add(minOuterTemperatureLabel);
		temperaturePanel.add(minOuterTemperatureValue);
		temperaturePanel.add(maxOuterTemperatureLabel);
		temperaturePanel.add(maxOuterTemperatureValue);
		
	}
	
	private JLabel createTextLabel(JLabel label, String text){
		label = new JLabel(text);
		label.setBorder(new EmptyBorder(3, 3, 3, 3));
		return label;
	}
	
	private JLabel createValueLabel(JLabel label){
		label = new JLabel();
		label.setBorder(new EmptyBorder(3, 3, 3, 3));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		return label;
	}
	
	public void setData(Route route){
		pointsValue.setText(Integer.toString(route.getTrackPoints().size()));
		distanceValue.setText(String.format("%.2f km", route.getDistance()));
		areaValue.setText(String.format("%.2f km\u00b2", route.getArea()));
		elapsedTimeValue.setText(String.format("%.2f h", route.getDuration()));
		drivingTimeValue.setText(String.format("%.2f h", route.getDrivingTime()));
		standStillValue.setText(String.format("%.2f h", route.getStandStillTime()));
		averageSpeedValue.setText(String.format("%d km/h", route.getAverageSpeed()));
		averageDrivingSpeedValue.setText(String.format("%d km/h", route.getAverageDrivingSpeed()));
		minSpeedValue.setText(String.format("%d km/h", route.getMinSpeed()));
		maxSpeedValue.setText(String.format("%d km/h", route.getMaxSpeed()));
		minHeightValue.setText(String.format("%d m", route.getMinHeight()));
		maxHeightValue.setText(String.format("%d m", route.getMaxHeight()));
		elevationUpHillValue.setText(String.format("%d m", route.getElevationUpHill()));
		elevationDownHillValue.setText(String.format("%d m", route.getElevationDownHill()));
		averageOuterTemperatureValue.setText(String.format("%.01f°C", route.getAverageOuterTemperature()));
		minOuterTemperatureValue.setText(String.format("%.01f\u00B0C", route.getMinOuterTemperature()));
		maxOuterTemperatureValue.setText(String.format("%.01f\u00B0C", route.getMaxOuterTemperature()));
	}
}
