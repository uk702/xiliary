package com.codeaffine.eclipse.swt.widget.scrollable;

import org.eclipse.swt.custom.StyledText;

import com.codeaffine.eclipse.swt.widget.scrollbar.FlatScrollBar;

class StyledTextVerticalScrollBarUpdater implements ScrollBarUpdater {

  private final FlatScrollBar scrollBar;
  private final StyledText styledText;

  StyledTextVerticalScrollBarUpdater( StyledText styledText, FlatScrollBar scrollBar ) {
    this.styledText = styledText;
    this.scrollBar = scrollBar;
  }

  @Override
  public void update() {
    int lineCount = styledText.getLineCount();
    scrollBar.setIncrement( 1 * SELECTION_RASTER_SMOOTH_FACTOR );
    scrollBar.setMaximum( lineCount * SELECTION_RASTER_SMOOTH_FACTOR );
    scrollBar.setMinimum( 0 );
    scrollBar.setPageIncrement( calculateThumb() );
    scrollBar.setThumb( calculateThumb() );
    scrollBar.setSelection( calculateSelection() );
  }

  private int calculateSelection() {
    int topIndex = styledText.getTopIndex();
    return topIndex * SELECTION_RASTER_SMOOTH_FACTOR;
  }

  int calculateThumb() {
    int height = calculateHeight();
    return SELECTION_RASTER_SMOOTH_FACTOR * ( height / styledText.getLineHeight() );
  }

  int calculateHeight() {
    return styledText.getClientArea().height;
  }
}