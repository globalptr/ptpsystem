/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class ddController {

    /**
     * Creates a new instance of ddController
     */
    public ddController() {
    }

    public void onDrop(DragDropEvent ddEvent) {
        String draggedId = ddEvent.getDragId();
        String droppedId = ddEvent.getDropId();
        Object data = ddEvent.getData();
        int i=0;
    }

}
