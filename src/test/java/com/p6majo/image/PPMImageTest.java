package com.p6majo.image;


import org.junit.jupiter.api.Test;

class PPMImageTest {

   @Test
    void render() {
        PPMImage test = new PPMImage(100,100,true);
        test.render("test.ppm");
    }

}