package com.vaadin.addon.touchkit.itest.navigationmanager;

import com.vaadin.addon.touchkit.AbstractTouchKitIntegrationTest;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickListener;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationEvent;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationEvent.Direction;
import com.vaadin.addon.touchkit.ui.NavigationManager.NavigationListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class SimpleTestWithNavigationButtons extends AbstractTouchKitIntegrationTest {
    
    @Override
    public String getDescription() {
        return "NavigationManager with navigation buttons.";
    }
    
    public SimpleTestWithNavigationButtons() {
        
        setSizeFull();
        
        final NavigationManager navigationManager = new NavigationManager();
        
        final CssLayout cL = new CssLayout();
        cL.setDebugId("l1");
        final CssLayout nL = new CssLayout();
        nL.setDebugId("l2");
        CssLayout pL = new CssLayout();
        pL.setDebugId("l0");
        final CssLayout yetAnother = new CssLayout();
        yetAnother.setDebugId("l3");
        
        
        cL.addComponent(new Label("CURR"));
        NavigationButton button = new NavigationButton("-->");
        cL.addComponent(button);
        button.setTargetView(nL);

        button = new NavigationButton("<--");
        cL.addComponent(button);
        button.setTargetView(pL);
        
        nL.addComponent(new Label("NEXT"));
        button = new NavigationButton("-->");
        nL.addComponent(button);
        button.setTargetView(yetAnother);

        button = new NavigationButton("<--");
        nL.addComponent(button);
        button.setTargetView(cL);
        
        
        yetAnother.addComponent(new Label("YET ANOTHER"));
        button = new NavigationButton("<--");
        yetAnother.addComponent(button);
        button.setTargetView(nL);

        
        pL.addComponent(new Label("PREV"));
        button = new NavigationButton("-->");
        pL.addComponent(button);
        button.setTargetView(cL);
        button.addClickListener(new NavigationButtonClickListener() {
            
            @Override
            public void buttonClick(NavigationButtonClickEvent event) {
                navigationManager.setNextComponent(nL);
            }
        });
                
        navigationManager.setPreviousComponent(pL);
        navigationManager.setCurrentComponent(cL);
        navigationManager.setNextComponent(nL);
        
        navigationManager.addListener(new NavigationListener() {
            @Override
            public void navigate(NavigationEvent event) {
                if(navigationManager.getCurrentComponent() == nL) {
                    if(event.getDirection() == Direction.FORWARD) {
                        navigationManager.setNextComponent(yetAnother);
                    }
                }
            }
        });
        
        addComponent(navigationManager);
        
        
        
    }

}
