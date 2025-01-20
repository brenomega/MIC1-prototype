package frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

import java.nio.channels.Pipe.SourceChannel;
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

        // Adicionar os componentes ao pane (código já existente)

        // Adicionar linhas de ligação
        createOrthogonalLines2(pane, "ALU", "SHIFTER");
        createOrthogonalLines2(pane, "A-LATCH", "AMUX");
        createOrthogonalLines3(pane, "B-LATCH", "ALU");
        createOrthogonalLines3(pane, "AMUX", "ALU");
        createOrthogonalLines4(pane, "B-LATCH", "MAR");
        createOrthogonalLines5(pane, "MBR", "AMUX");
        createOrthogonalLines6(pane, "SHIFTER", "REG");
        createOrthogonalLines7(pane, "ALU", "M-SEQ");
        createOrthogonalLines8(pane, "MPC", "CONTROL");
        createOrthogonalLines9(pane, "MPC", "INCR");
        createOrthogonalLines10(pane, "MMUX", "MPC");
        createOrthogonalLines11(pane, "INCR", "MMUX");
        createOrthogonalLines12(pane, "M-SEQ", "MMUX");
        createOrthogonalLines13(pane, "A-DEC", "REG");
        createOrthogonalLines14(pane, "B-DEC", "REG");
        createOrthogonalLines15(pane, "C-DEC", "REG");
        createOrthogonalLines16(pane, "MIR", "A-DEC");
        // connectComponentsOrthogonally(pane, "MAR", "MBR");
        // connectComponentsOrthogonally(pane, "MPC", "INCR");
        createOrthogonalLinesReg(pane, "REG", "A-LATCH");
        
        createOrthogonalLinesReg(pane, "REG", "B-LATCH");      

        // Continue conectando os componentes conforme necessário

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

    
    public void createOrthogonalLinesReg(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth();
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines2(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth();
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight();
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY() ;
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, midX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines3(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth()/2;
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight();
    
        // Ponto central do componente de destino
        double endX = sourceBounds.getMinX() + sourceBounds.getWidth()/2;;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines4(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2;
        double startY = targetBounds.getMinY() + targetBounds.getHeight() / 2;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() + targetBounds.getWidth();
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines5(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth();
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() - 15 + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines6(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2;
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight();
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX();
        double endY = targetBounds.getMinY() + targetBounds.getHeight() / 2;
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines7(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() - 8;
        double startY = sourceBounds.getMinY() + 20;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX();
        double endY = targetBounds.getMinY() + targetBounds.getHeight() / 2;
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);

        // Ponto central do componente de origem
         startX = sourceBounds.getMinX() + sourceBounds.getWidth() - 5;
         startY = sourceBounds.getMinY() + 9;
    
        // Ponto central do componente de destino
         endX = targetBounds.getMinX();
         endY = targetBounds.getMinY() + targetBounds.getHeight() / 2;
    
        // Determina ponto intermediário para criar ângulos retos
         midX = startX;
         midY = endY;
    
        // Linhas horizontais e verticais
         line1 = new Line(startX, startY, midX, startY); // Linha horizontal
         line2 = new Line(startX, startY, endX, startY);     // Linha vertical
         line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);

        }

    }

    public void createOrthogonalLines8(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2;
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight();
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines9(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX();
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() + targetBounds.getWidth();
        double endY = targetBounds.getMinY() + targetBounds.getHeight() /2;
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines10(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2;
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight();
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines11(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Coordenadas do ponto inicial (source)
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2;
        double startY = sourceBounds.getMinY();

        // Coordenadas do ponto final (target)
        double endX = targetBounds.getMinX() - 5 + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();

        // Determina o ponto intermediário para o ângulo reto
        double midX = startX; // Mantém a mesma coordenada X
        double midY = startY - 50; // Vai para cima (90 graus), ajustando -50 para a altura desejada

        // Criação das linhas:
        Line line1 = new Line(startX, startY, midX, midY); // Linha que vai para cima
        Line line2 = new Line(midX, midY, endX, midY);    // Linha que vira para a direita (horizontal)

        // Adiciona as linhas ao painel ou grupo
        pane.getChildren().addAll(line1, line2);

        }

    }

    public void createOrthogonalLines12(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
            // Coordenadas do ponto inicial (source)
        double startX = sourceBounds.getMinX() + sourceBounds.getWidth() / 2; // Centro do componente de origem
        double startY = sourceBounds.getMinY(); // Parte superior do componente de origem

        // Coordenadas do ponto final (target)
        double endX = targetBounds.getMinX(); // Parte esquerda do componente de destino
        double endY = targetBounds.getMinY() + targetBounds.getHeight() / 2; // Centro do componente de destino

        // Determina o ponto intermediário para criar ângulos retos
        double midX = startX; // Ponto intermediário mantém o mesmo X inicial
        double midY = endY;   // Ponto intermediário sobe até o Y do destino

        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, midY); // Linha que sobe (vertical)
        Line line2 = new Line(midX, midY, endX, midY);    // Linha que vai para a direita (horizontal)

        // Adiciona as linhas ao grupo ou painel
        Line[] linhafinal = new Line[]{line1, line2};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines13(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX();
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() + 15 + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines14(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX();
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() - 5 + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines15(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Ponto central do componente de origem
        double startX = sourceBounds.getMinX();
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight() / 2;
    
        // Ponto central do componente de destino
        double endX = targetBounds.getMinX() - 20 + targetBounds.getWidth() / 2;
        double endY = targetBounds.getMinY();
    
        // Determina ponto intermediário para criar ângulos retos
        double midX = startX;
        double midY = endY;
    
        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX, startY); // Linha horizontal
        Line line2 = new Line(startX, startY, endX, startY);     // Linha vertical
        Line line3 = new Line(endX, startY, endX, endY);       // Linha horizontal final
    
        Line[] linhafinal = new Line[]{line1, line2, line3};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }

    public void createOrthogonalLines16(Pane pane, String sourceName, String targetName) {
        Component source = components.get(sourceName);
        Component target = components.get(targetName);
        
        if (source != null && target != null) {

        Bounds sourceBounds = source.getImageView().getBoundsInParent();
        Bounds targetBounds = target.getImageView().getBoundsInParent();
    
        // Coordenadas do ponto inicial (source)
        double startX = sourceBounds.getMinX() - 45 + sourceBounds.getWidth(); // Ligeiramente à direita do centro do componente de origem
        double startY = sourceBounds.getMinY() + sourceBounds.getHeight();    // Parte inferior do componente de origem

        // Coordenadas do ponto final (target)
        double endX = targetBounds.getMinX() + targetBounds.getWidth(); // Ligeiramente à direita do centro do componente de destino
        double endY = targetBounds.getMinY() + targetBounds.getHeight() /2; // Parte inferior do componente de destino

        // Pontos intermediários para criar o trajeto
        double midX1 = startX;         // Desce verticalmente no mesmo X inicial
        double midY1 = startY + 20;    // Move 20 pixels para baixo

        double midX2 = midX1 + 50;     // Move 50 pixels para a direita
        double midY2 = midY1;          // Mantém o mesmo Y após descer

        double midX3 = midX2;          // Mantém o mesmo X
        double midY3 = endY - 20;      // Sobe até próximo do ponto final

        double midX4 = endX;           // Vai para a esquerda até o destino
        double midY4 = endY;           // Alcança o destino

        // Linhas horizontais e verticais
        Line line1 = new Line(startX, startY, midX1, midY1); // Linha descendo
        Line line2 = new Line(midX1, midY1, midX2, midY2);   // Linha para a direita
        Line line3 = new Line(midX2, midY2, midX3, midY3);   // Linha subindo
        Line line4 = new Line(midX3, midY3, midX4, midY4);   // Linha para a esquerda (destino)

        // Adiciona as linhas ao grupo ou painel
        Line[] linhafinal = new Line[]{line1, line2, line3, line4};
        connectComponentsOrthogonally(pane, linhafinal);
        }

    }


    public void connectComponentsOrthogonally(Pane pane, Line[] lines) {       
    
            for (Line line : lines) {
                line.setStrokeWidth(2); // Espessura da linha
                line.setStroke(Color.BLACK); // Cor da linha
                pane.getChildren().add(line); // Adiciona a linha ao pane
            }
        
    }
    

}
