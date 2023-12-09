package fr.ensicaen.regatta.mvp.model;

import java.io.*;

public class Polar {
    private final String PolarFileName;

    public Polar(String FileName) {
        PolarFileName = FileName;
    }

    public double getSpeedFromFile(double angle, double speed) throws Exception{
        int i = 0, index1 = 0, index2 = 0;
        String st;

        File file = new File(PolarFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        st = br.readLine();
        String [] windSpeedSTR = st.split("\t");

        int [] windSpeed = new int[windSpeedSTR.length];
        int [] angles = new int[19];
        double [][] data = new double[19][windSpeedSTR.length];

        for(int n = 0; n < windSpeed.length; n++) {
            windSpeed[n] = Integer.parseInt(windSpeedSTR[n]);
        }
        while ((st = br.readLine()) != null) {
            String [] lines = st.split("\t");
            for (int j = 0; j < windSpeed.length; j++) {
                data[i][j] = Double.parseDouble(lines[j + 1]);
            }
            angles[i] = Integer.parseInt(lines[0]);
            i++;
        }
        
        for(i = 1; i<angles.length; i++) {
            if (angle > angles[i - 1] && angle <= angles[i]) {
                index2 = i;
            }
        }

        for(i = 1; i<windSpeed.length; i++) {
            if(speed > windSpeed[i -1] && speed < windSpeed[i]) {
                index1 = i;
            } else if (speed == windSpeed[i]) {
                return (data[index2][i]);
            }
        }

        if (index1 > 0)
            return (Math.abs(data[index2][index1 - 1] - data[index2][index1]) /2 + data[index2][index1 - 1]);
        else
            return data[index2][0];
    }
}
