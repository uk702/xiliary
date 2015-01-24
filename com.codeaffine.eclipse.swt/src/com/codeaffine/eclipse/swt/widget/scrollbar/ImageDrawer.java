package com.codeaffine.eclipse.swt.widget.scrollbar;

import static java.lang.Math.min;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

class ImageDrawer {

  private final int maxExpansion;

  private Color color;

  ImageDrawer( int expansion  ) {
    this.maxExpansion = expansion;
    this.color = Display.getCurrent().getSystemColor( SWT.COLOR_WIDGET_DARK_SHADOW );
  }

  void setColor( Color color ) {
    if( color != null ) {
      this.color = color;
    }
  }

  Color getColor() {
    return color;
  }

  Image draw( int width, int height ) {
    Image result = new Image( getDisplay(), width, height );
    GC gc = new GC( result );
    try {
      draw( gc, width, height );
    } finally {
      gc.dispose();
    }
    return result;
  }

  private void draw( GC gc, int width, int height ) {
    gc.setBackground( Display.getCurrent().getSystemColor( SWT.COLOR_LIST_BACKGROUND ) );
    gc.fillRectangle( 0, 0, width, height );
    gc.setBackground( color );
    gc.setAdvanced( true );
    gc.setAntialias( SWT.ON );
    gc.setAlpha( 170 );
    int arc = min( width, height ) == 1 ? 1 : maxExpansion + 2;
    gc.fillRoundRectangle( 0, 0, width, height, arc, arc );
  }

  private static Display getDisplay() {
    return Display.getCurrent();
  }
}