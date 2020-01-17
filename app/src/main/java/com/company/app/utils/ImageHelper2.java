package com.company.app.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class ImageHelper2 {
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels, String title) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        Rect rectTL = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Rect rectTR = new Rect(bitmap.getWidth() / 2, 0, bitmap.getWidth(), bitmap.getHeight() / 2);
        Rect rectBR = new Rect(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth(), bitmap.getHeight());
        Rect rectBL = new Rect(0, bitmap.getHeight() / 2, bitmap.getWidth() / 2, bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        // Fill in upper right corner
        canvas.drawRect(rectTR, paint);
        // Fill in bottom corners
        canvas.drawRect(rectTL, paint);
        canvas.drawRect(rectBL, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
//        Paint mTextPaint = new Paint();
//        mTextPaint.setColor(Color.BLACK);
//        mTextPaint.setTextSize(300);
//        mTextPaint.setFakeBoldText(true);
//        canvas.drawText(title, bitmap.getWidth() / 2-340 , bitmap.getHeight() / 2+50, mTextPaint);
        return output;
    }
}