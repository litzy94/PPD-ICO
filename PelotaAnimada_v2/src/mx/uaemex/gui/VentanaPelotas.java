package mx.uaemex.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mx.uaemex.animaciones.PelotaRebotadora;

public class VentanaPelotas extends JFrame {

  public VentanaPelotas() {
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    PanelPelota panelPelota = new PanelPelota();
    add(panelPelota);

    JPanel panelBotones = new JPanel(new FlowLayout());

    JButton btnAgregarPelota = new JButton("Agregar Pelota");
    panelBotones.add(btnAgregarPelota);
    JButton btnSalir = new JButton("Salir");
    panelBotones.add(btnSalir);
    add(panelBotones, BorderLayout.SOUTH);

    btnAgregarPelota.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //for (int i = 0; i < 4; i++) {
          Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
              PelotaRebotadora pelota = new PelotaRebotadora();
              panelPelota.add(pelota);

              while (true) {
                pelota.mueve_pelota(panelPelota.getBounds());
                panelPelota.paint(panelPelota.getGraphics());
                try {
                  Thread.sleep(2);
                } catch (InterruptedException ex) {
                  //TODO something...
                }
              }
            }
          });
          hilo.start();
//          try {
//            Thread.sleep(1000);
//          } catch (InterruptedException ex) {
//            //TODO something...
//          }
//        }
      }
    });

    btnSalir.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    setVisible(true);
  }

}
