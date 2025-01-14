package frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class SimulatorController {

    private final HashMap<String, Component> components = new HashMap<>();

    public Pane createSimulatorPane() {
        Pane pane = new Pane();

        
        // Exemplo: Adicionar ALU
        Component alu = new Component("ALU", "img/datapath/alu.png", "img/datapath/alu_active.png", 600, 400);
        components.put("ALU", alu);
        pane.getChildren().add(alu.getImageView());

        Component adec = new Component("A-DEC", "img/datapath/a-dec.png", "img/datapath/a-dec_active.png", 720, 75);
        components.put("A-DEC", adec);
        pane.getChildren().add(adec.getImageView());

        Component alatch = new Component("A-LATCH", "img/datapath/a-latch.png", "img/datapath/a-latch_active.png", 605, 270);
        components.put("A-LATCH", alatch);
        pane.getChildren().add(alatch.getImageView());

        Component amux = new Component("AMUX", "img/datapath/amux.png", "img/datapath/amux_active.png", 600, 350);
        components.put("AMUX", amux);
        pane.getChildren().add(amux.getImageView());

        Component bdec = new Component("B-DEC", "img/datapath/b-dec.png", "img/datapath/b-dec_active.png", 720, 40);
        components.put("B-DEC", bdec);
        pane.getChildren().add(bdec.getImageView());

        Component blatch = new Component("B-LATCH", "img/datapath/b-latch.png", "img/datapath/b-latch_active.png", 680, 270);
        components.put("B-LATCH", blatch);
        pane.getChildren().add(blatch.getImageView());

        Component cdec = new Component("C-DEC", "img/datapath/c-dec.png", "img/datapath/c-dec_active.png", 720, 5);
        components.put("C-DEC", cdec);
        pane.getChildren().add(cdec.getImageView());

        Component clock = new Component("CLOCK", "img/datapath/clock.png", "img/datapath/clock_active_1.png", 900, 100);
        components.put("CLOCK", clock);
        pane.getChildren().add(clock.getImageView());

        Component control = new Component("CONTROL", "img/datapath/control.png", "img/datapath/control_active.png", 820, 280);
        components.put("CONTROL", control);
        pane.getChildren().add(control.getImageView());

        Component incr = new Component("INCR", "img/datapath/incr.png", "img/datapath/incr_active.png", 830, 230);
        components.put("INCR", incr);
        pane.getChildren().add(incr.getImageView());

        Component mseq = new Component("M-SEQ", "img/datapath/m-seq.png", "img/datapath/m-seq_active.png", 760, 400);
        components.put("M-SEQ", mseq);
        pane.getChildren().add(mseq.getImageView());

        Component mar = new Component("MAR", "img/datapath/mar.png", "img/datapath/mar_active.png", 380, 290);
        components.put("MAR", mar);
        pane.getChildren().add(mar.getImageView());

        Component mbr = new Component("MBR", "img/datapath/mbr.png", "img/datapath/mbr_active.png", 400, 320);
        components.put("MBR", mbr);
        pane.getChildren().add(mbr.getImageView());

        Component mir = new Component("MIR", "img/datapath/mir.png", "img/datapath/mir_active.png", 820, 340);
        components.put("MIR", mir);
        pane.getChildren().add(mir.getImageView());

        Component mmux = new Component("MMUX", "img/datapath/mmux.png", "img/datapath/mmux_active.png", 920, 180);
        components.put("MMUX", mmux);
        pane.getChildren().add(mmux.getImageView());

        Component reg = new Component("REG", "img/datapath/reg.png", "img/datapath/reg_active.png", 500, 140);
        components.put("REG", reg);
        pane.getChildren().add(reg.getImageView());


        Component shifter = new Component("SHIFTER", "img/datapath/shifter.png", "img/datapath/shifter_active.png", 620, 500);
        components.put("SHIFTER", shifter);
        pane.getChildren().add(shifter.getImageView());

        // Exemplo: Adicionar MPC
        Component mpc = new Component("MPC", "img/datapath/mpc.png", "img/datapath/mpc_active.png", 920, 230);
        components.put("MPC", mpc);
        pane.getChildren().add(mpc.getImageView());

        // Mais componentes podem ser adicionados aqui
        return pane;
    }

    public void activateComponent(String name) {
        Component component = components.get(name);
        if (component != null) {
            component.activate();
        }
    }

    public void deactivateComponent(String name) {
        Component component = components.get(name);
        if (component != null) {
            component.deactivate();
        }
    }
}
