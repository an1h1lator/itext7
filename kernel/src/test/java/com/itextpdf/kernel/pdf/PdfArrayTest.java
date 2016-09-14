package com.itextpdf.kernel.pdf;

import com.itextpdf.io.source.ByteArrayOutputStream;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PdfArrayTest {

    @Test
    public void testValuesIndirectContains() {
        PdfDocument doc = new PdfDocument(new PdfWriter(new ByteArrayOutputStream()));

        PdfArray array = new PdfArray();
        array.add(new PdfNumber(0).makeIndirect(doc).getIndirectReference());
        array.add(new PdfNumber(1).makeIndirect(doc).getIndirectReference());
        array.add(new PdfNumber(2).makeIndirect(doc));
        array.add(new PdfNumber(3).makeIndirect(doc));
        array.add(new PdfNumber(4));
        array.add(new PdfNumber(5));

        Assert.assertTrue(array.contains(array.get(0, false)));
        Assert.assertTrue(array.contains(array.get(1, false)));
        Assert.assertTrue(array.contains(array.get(2).getIndirectReference()));
        Assert.assertTrue(array.contains(array.get(3).getIndirectReference()));
        Assert.assertTrue(array.contains(array.get(4)));
        Assert.assertTrue(array.contains(array.get(5)));
    }

    @Test
    public void testValuesIndirectRemove() {
        PdfDocument doc = new PdfDocument(new PdfWriter(new ByteArrayOutputStream()));

        PdfArray array = new PdfArray();
        array.add(new PdfNumber(1).makeIndirect(doc).getIndirectReference());
        array.add(new PdfNumber(2).makeIndirect(doc).getIndirectReference());
        array.add(new PdfNumber(3).makeIndirect(doc));
        array.add(new PdfNumber(4).makeIndirect(doc));
        array.add(new PdfNumber(5));
        array.add(new PdfNumber(6));

        array.remove(array.get(0, false));
        array.remove(array.get(0, false));
        array.remove(array.get(0).getIndirectReference());
        array.remove(array.get(0).getIndirectReference());
        array.remove(array.get(0));
        array.remove(array.get(0));

        Assert.assertEquals(0, array.size());
    }
}