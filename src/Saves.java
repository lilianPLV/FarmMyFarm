import com.sun.tools.javac.code.Lint;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.*;
import java.sql.Timestamp;

public class Saves {

    public static void reset() {
        Land.nb_carrots = 0;
        Land.nb_carrots_take = 0;
        Land.nb_mais = 0;
        Land.nb_mais_take = 0;
        Land.nb_ble = 0;
        Land.nb_ble_take = 0;
        Land.argent = 999999999;
        Land.nb_sheep = 0;
        Land.nb_chicken = 0;
        Land.nb_cow = 0;
        Land.nb_laine = 0;
        Land.nb_egg = 0;
        Land.nb_lait = 0;
    }

    public static void saveChoice(String PATH) {
        Sauvegardes.PATH = PATH;
    }

    public static void save(Land land) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(Sauvegardes.PATH))) {
            pw.println(land.getNb_carrots());
            pw.println(land.getNb_carrots_take());
            pw.println(land.getNb_mais());
            pw.println(land.getNb_mais_take());
            pw.println(land.getNb_ble());
            pw.println(land.getNb_ble_take());
            pw.println(land.getArgent());
            pw.println(land.getAlready_unlock());
            pw.println(land.getNb_sheep());
            pw.println(land.getNb_chicken());
            pw.println(land.getNb_cow());
            pw.println(land.getNb_laine());
            pw.println(land.getNb_egg());
            pw.println(land.getNb_lait());
            pw.println(land.getTime());

            pw.println("---champ1---");
            for (Node node : land.getChamp1().getChildren()) {
                Integer col = GridPane.getColumnIndex(node);
                Integer row = GridPane.getRowIndex(node);
                if (col == null || row == null) continue;
                String id = node.getId() != null ? node.getId() : "empty";
                pw.println(col + "," + row + "," + id);
            }

            pw.println("---champ2---");
            for (Node node : land.getChamp2().getChildren()) {
                Integer col = GridPane.getColumnIndex(node);
                Integer row = GridPane.getRowIndex(node);
                if (col == null || row == null) continue;
                String id = node.getId() != null ? node.getId() : "empty";
                pw.println(col + "," + row + "," + id);
            }

            pw.println("---étable---");
            for (Node node : land.getEtable().getChildren()) {
                Integer col = GridPane.getColumnIndex(node);
                Integer row = GridPane.getRowIndex(node);
                if (col == null || row == null) continue;
                String id = node.getId() != null ? node.getId() : "empty_animal";
                pw.println(col + "," + row + "," + id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(Land land) {
        reset();
        Land.timeAfter = new Timestamp(System.currentTimeMillis());
        File file = new File(Sauvegardes.PATH);
        if (!file.exists()) {
            System.out.println("Aucune sauvegarde trouvée.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            line = br.readLine(); if (line == null) return; Land.nb_carrots = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_carrots_take = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_mais = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_mais_take = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_ble = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_ble_take = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.argent = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; land.already_unlock = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_sheep = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_chicken = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_cow = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_laine = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_egg = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.nb_lait = Integer.parseInt(line);
            line = br.readLine(); if (line == null) return; Land.time = Timestamp.valueOf(line);


            while ((line = br.readLine()) != null && !line.equals("---champ2---")) {
                applyCell(land.getChamp1(), line);
            }
            while ((line = br.readLine()) != null && !line.equals("---étable---")) {
                applyCell(land.getChamp2(), line);
            }
            while ((line = br.readLine()) != null) {
                applyCell(land.getEtable(), line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void applyCell(GridPane grid, String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) return;

        int col = Integer.parseInt(parts[0]);
        int row = Integer.parseInt(parts[1]);
        String id = parts[2];
        long secondes = (Land.timeAfter.getTime() - Land.time.getTime()) / 1000;

        for (Node node : grid.getChildren()) {
            Integer c = GridPane.getColumnIndex(node);
            Integer r = GridPane.getRowIndex(node);
            if (c != null && r != null && c == col && r == row) {
                node.setId(id);
                Pane pane = (Pane) node;
                switch (id) {
                    case "empty" -> pane.setStyle("-fx-background-color: #693501;");
                    case "planted_mais" -> {
                        if (secondes >= 10) {
                            node.setId("ready_mais");
                            pane.setStyle("-fx-background-color: Yellow;");
                        }else {
                            pane.setStyle("-fx-background-color: Green;");
                        }
                    }
                    case "planted_carrot" -> {
                        if (secondes >= 4) {
                            node.setId("ready_carrot");
                            pane.setStyle("-fx-background-color: Yellow;");
                        }else {
                            pane.setStyle("-fx-background-color: Green;");
                        }
                    }
                    case "planted_ble" -> {
                        if (secondes >= 20) {
                            node.setId("ready_ble");
                            pane.setStyle("-fx-background-color: Yellow;");
                        }else {
                            pane.setStyle("-fx-background-color: Green;");
                        }
                    }
                    case "animal_sheep" -> {
                        if (secondes >= 5) {
                            node.setId("ready_sheep");
                            pane.setStyle("-fx-background-color: Yellow;");
                        }else {
                            pane.setStyle("-fx-background-color: Green;");
                        }
                    }
                    case "animal_chicken" -> {
                        if (secondes >= 5) {
                            node.setId("ready_chicken");
                            pane.setStyle("-fx-background-color: Yellow;");
                        }else {
                            pane.setStyle("-fx-background-color: Green;");
                        }
                    }
                    case "animal_cow" -> {
                        if (secondes >= 5) {
                            node.setId("ready_cow");
                            pane.setStyle("-fx-background-color: Yellow;");
                        }else {
                            pane.setStyle("-fx-background-color: Green;");
                        }
                    }
                    case "ready_carrot", "ready_mais", "ready_ble", "ready_sheep", "ready_chicken", "ready_cow" -> pane.setStyle("-fx-background-color: Yellow;");
                    default -> pane.setStyle("-fx-background-color: #693501;");
                }
                break;
            }
        }
    }
}