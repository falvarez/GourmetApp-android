package com.jugarte.gourmet.utils;

import android.content.Context;

public class ClipboardUtils {

    public static void copyToClipboard(Context context, String textToCopy) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", textToCopy);
        clipboard.setPrimaryClip(clip);
    }
}
