/**
 * (C)opyright 2013, by Object Refinery Limited
 */
package com.orsoncharts.graphics3d;

import java.awt.Color;

/**
 * Represents a face in one {@link Object3D}, defined in terms of vertex
 * indices.
 */
public class Face {

  private int offset;

  /** The indices of the vertices representing this face. */
  private int[] vertices;

  /** The color of the face. */
  private Color color;

  /**
   * A flag to track whether or not the face was drawn in the most recent
   * rendering.
   */
  private boolean rendered;

  /**
   * Creates a new face.
   *
   * @param vertices  the indices of the vertices.
   * @param color  the face color (<code>null</code> not permitted).
   */
  public Face(int[] vertices, Color color) {
    if (vertices.length < 3) {
      throw new IllegalArgumentException("Faces must have at least 3 vertices.");
    }
    ArgChecks.nullNotPermitted(color, "color");
    this.vertices = vertices;
    this.offset = 0;
    this.color = color;
  }

  /**
   * Returns the offset to add to the vertex indices.
   *
   * @return The offset.
   */
  public int getOffset() {
    return this.offset;
  }

  /**
   * Sets the offset to add to the vertex indices.
   *
   * @param offset  the offset.
   */
  public void setOffset(int offset) {
    this.offset = offset;
  }

 /**
  * Returns the face color.  When rendering, a different shade will be used
  * to simulate lighting.
  *
  * @return The color (never <code>null</code>).
  */
 public Color getColor() {
   return this.color;
 }

 /**
  * Returns the number of vertices in this face.
  *
  * @return The number of vertices in this face.
  */
  public int getVertexCount() {
    return this.vertices.length;
  }

  /**
   * Returns the index for the specified vertex.
   *
   * @param i  the vertex index.
   *
   * @return  The index.
   */
  public int getVertexIndex(int i) {
    return this.vertices[i] + this.offset;
  }

  /**
   * Calculates the normal vector for this face.
   *
   * @param points  the vertices of the object that this face belongs to
   *     (these can be in world or eye coordinates).
   *
   * @return The normal vector.
   */
  public double[] calculateNormal(Point3D[] points) {
    int iA = this.vertices[0] + this.offset;
    int iB = this.vertices[1] + this.offset;
    int iC = this.vertices[2] + this.offset;
    double aX = points[iA].x;
    double aY = points[iA].y;
    double aZ = points[iA].z;
    double bX = points[iB].x;
    double bY = points[iB].y;
    double bZ = points[iB].z;
    double cX = points[iC].x;
    double cY = points[iC].y;
    double cZ = points[iC].z;
    double u1 = bX - aX, u2 = bY - aY, u3 = bZ - aZ;
    double v1 = cX - aX, v2 = cY - aY, v3 = cZ - aZ;
    double a = u2 * v3 - u3 * v2,
           b = u3 * v1 - u1 * v3,
           c = u1 * v2 - u2 * v1,
           len = Math.sqrt(a * a + b * b + c * c);
           a /= len; b /= len; c /= len;
    return new double[] {a, b, c};
  }

  /**
   * Returns the average z-value.
   *
   * @param points  the points.
   *
   * @return The average z-value.
   */
  public float calculateAverageZValue(Point3D[] points) {
    float total = 0.0f;
    for (int i = 0; i < this.vertices.length; i++) {
      total = total + points[this.vertices[i] + this.offset].z;
    }
    return total / this.vertices.length;
  }

  /**
   * A flag that indicates whether or not the face is rendered.
   * @return 
   */
  public boolean isRendered() {
    return this.rendered;
  }

  /**
   * Sets a flag indicating whether or not this face was rendered.  This method
   * is called by the Panel3D class while rendering.
   * 
   * @param rendered  the flag.
   */
  public void setRendered(boolean rendered) {
    this.rendered = rendered;
  }

  public String toString() {
    String result = "[";
    for (int i = 0; i < this.vertices.length; i++) {
      result = result + this.vertices[i];
      if (i < this.vertices.length - 1) {
        result = result + ", ";
      }
    }
    return result + "]";
  }
}