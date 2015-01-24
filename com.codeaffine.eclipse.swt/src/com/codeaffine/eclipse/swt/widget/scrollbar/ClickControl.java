package com.codeaffine.eclipse.swt.widget.scrollbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.codeaffine.eclipse.swt.util.ButtonClick;
import com.codeaffine.eclipse.swt.util.MouseDownActionTimer;
import com.codeaffine.eclipse.swt.util.MouseDownActionTimer.TimerAction;

class ClickControl extends ControlAdapter implements ViewComponent, TimerAction, MouseListener, MouseTrackListener {

  private final MouseDownActionTimer mouseDownActionTimer;
  private final ClickAction clickAction;
  private final ButtonClick buttonClick;
  private final Label control;
  private final ImageUpdate imageUpdate;

  public interface ClickAction extends Runnable {
    void setCoordinates( int x, int y );
  }

  ClickControl( Composite parent, ClickAction clickAction, int maxExtension  ) {
    this.control = new Label( parent, SWT.NONE );
    this.imageUpdate = new ImageUpdate( control, maxExtension );
    this.buttonClick = new ButtonClick();
    this.mouseDownActionTimer = new MouseDownActionTimer( this, buttonClick, control.getDisplay() );
    this.clickAction = clickAction;
    this.control.addMouseTrackListener( this );
    this.control.addMouseListener( this );
    this.control.addControlListener( this );
  }

  @Override
  public void controlResized( ControlEvent event ) {
    imageUpdate.update();
  }

  @Override
  public Label getControl() {
    return control;
  }

  @Override
  public void mouseDown( MouseEvent event ) {
    buttonClick.arm( event );
    clickAction.setCoordinates( event.x, event.y );
    mouseDownActionTimer.activate();
  }

  @Override
  public void mouseUp( MouseEvent event ) {
    buttonClick.trigger( event, clickAction );
  }

  @Override
  public void run() {
    clickAction.run();
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public void mouseExit( MouseEvent event ) {
    buttonClick.disarm();
  }

  void setColor( Color color ) {
    imageUpdate.setColor( color );
  }

  Color getColor() {
    return imageUpdate.getColor();
  }

  @Override
  public void mouseEnter( MouseEvent event ) {}

  @Override
  public void mouseHover( MouseEvent event ) {}

  @Override
  public void mouseDoubleClick( MouseEvent event ) {}
}