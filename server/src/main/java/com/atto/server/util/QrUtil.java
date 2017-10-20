package com.atto.server.util;

import java.io.ByteArrayOutputStream;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

public class QrUtil {

    public static byte[] createQrCodeForUrl(String url) {
        ByteArrayOutputStream bout =
                QRCode.from(url)
                        .withSize(33, 33)
                        .to(ImageType.PNG)
                        .stream();
        return bout.toByteArray();
    }
}
