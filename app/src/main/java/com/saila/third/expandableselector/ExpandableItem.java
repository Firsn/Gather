package com.saila.third.expandableselector;

public class ExpandableItem {

  private static final int NO_ID = -1;

  private int resourceId = NO_ID;
  private final int backgroundId;
  private final String title;

  public ExpandableItem() {
    this(NO_ID, null);
  }

  public ExpandableItem(int backgroundId) {
    this(backgroundId, null);
  }

  public ExpandableItem(String title) {
    this(NO_ID, title);
  }

  private ExpandableItem(int backgroundId, String title) {
    this.backgroundId = backgroundId;
    this.title = title;
  }

  public int getBackgroundId() {
    return backgroundId;
  }

  public String getTitle() {
    return title;
  }

  public void setResourceId(int resourceId) {
    this.resourceId = resourceId;
  }

  public int getResourceId() {
    return resourceId;
  }

  public boolean hasResourceId() {
    return resourceId != NO_ID;
  }

  public boolean hasBackgroundId() {
    return backgroundId != NO_ID;
  }

  public boolean hasTitle() {
    return title != null;
  }
}
