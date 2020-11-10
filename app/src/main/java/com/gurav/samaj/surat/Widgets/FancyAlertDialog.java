package com.gurav.samaj.surat.Widgets;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gurav.samaj.surat.R;

public class FancyAlertDialog {
    private Activity activity;
    private Animation animation;
    private int bgColor;
    private boolean cancel;
    private int icon;
    private String message;
    private int nBtnColor;
    private boolean nBtnVisibe;
    private FancyAlertDialogListener nListener;
    private String negativeBtnText;
    private int pBtnColor;
    private FancyAlertDialogListener pListener;
    private String positiveBtnText;
    private String title;
    private Icon visibility;

    public static class Builder {
        /* access modifiers changed from: private */
        public Activity activity;
        /* access modifiers changed from: private */
        public Animation animation;
        /* access modifiers changed from: private */
        public int bgColor;
        /* access modifiers changed from: private */
        public boolean cancel;
        /* access modifiers changed from: private */
        public int icon;
        /* access modifiers changed from: private */
        public String message;
        /* access modifiers changed from: private */
        public int nBtnColor;
        /* access modifiers changed from: private */
        public boolean nBtnVisibe;
        /* access modifiers changed from: private */
        public FancyAlertDialogListener nListener;
        /* access modifiers changed from: private */
        public String negativeBtnText;
        /* access modifiers changed from: private */
        public int pBtnColor;
        /* access modifiers changed from: private */
        public FancyAlertDialogListener pListener;
        /* access modifiers changed from: private */
        public String positiveBtnText;
        /* access modifiers changed from: private */
        public String title;
        /* access modifiers changed from: private */
        public Icon visibility;

        public Builder(Activity activity2) {
            this.activity = activity2;
        }

        public Builder setTitle(String title2) {
            this.title = title2;
            return this;
        }

        public Builder setBackgroundColor(int bgColor2) {
            this.bgColor = bgColor2;
            return this;
        }

        public Builder setMessage(String message2) {
            this.message = message2;
            return this;
        }

        public Builder setPositiveBtnText(String positiveBtnText2) {
            this.positiveBtnText = positiveBtnText2;
            return this;
        }

        public Builder setPositiveBtnBackground(int pBtnColor2) {
            this.pBtnColor = pBtnColor2;
            return this;
        }

        public Builder setNegativeBtnText(String negativeBtnText2) {
            this.negativeBtnText = negativeBtnText2;
            return this;
        }

        public Builder setNegativeBtnBackground(int nBtnColor2) {
            this.nBtnColor = nBtnColor2;
            return this;
        }

        public Builder setIcon(int icon2, Icon visibility2) {
            this.icon = icon2;
            this.visibility = visibility2;
            return this;
        }

        public Builder setAnimation(Animation animation2) {
            this.animation = animation2;
            return this;
        }

        public Builder setnBtnVisible(boolean nBtnVisibe2) {
            this.nBtnVisibe = nBtnVisibe2;
            return this;
        }

        public Builder OnPositiveClicked(FancyAlertDialogListener pListener2) {
            this.pListener = pListener2;
            return this;
        }

        public Builder OnNegativeClicked(FancyAlertDialogListener nListener2) {
            this.nListener = nListener2;
            return this;
        }

        public Builder isCancellable(boolean cancel2) {
            this.cancel = cancel2;
            return this;
        }

        public FancyAlertDialog build() {
            final Dialog dialog;
            if (this.animation == Animation.POP) {
                dialog = new Dialog(this.activity, R.style.PopTheme);
            } else if (this.animation == Animation.SIDE) {
                dialog = new Dialog(this.activity, R.style.SideTheme);
            } else if (this.animation == Animation.SLIDE) {
                dialog = new Dialog(this.activity, R.style.SlideTheme);
            } else {
                dialog = new Dialog(this.activity);
            }
            dialog.requestWindowFeature(1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.setCancelable(this.cancel);
            dialog.setContentView(R.layout.fancyalertdialog);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            View view = dialog.findViewById(R.id.background);
            TextView message1 = (TextView) dialog.findViewById(R.id.message);
            ImageView iconImg = (ImageView) dialog.findViewById(R.id.icon);
            Button nBtn = (Button) dialog.findViewById(R.id.negativeBtn);
            Button pBtn = (Button) dialog.findViewById(R.id.positiveBtn);
            ((TextView) dialog.findViewById(R.id.title)).setText(this.title);
            message1.setText(this.message);
            if (!this.nBtnVisibe) {
                nBtn.setVisibility(4);
            } else {
                nBtn.setVisibility(0);
            }
            if (this.positiveBtnText != null) {
                pBtn.setText(this.positiveBtnText);
            }
            if (this.pBtnColor != 0) {
                ((GradientDrawable) pBtn.getBackground()).setColor(this.pBtnColor);
            }
            if (this.nBtnColor != 0) {
                ((GradientDrawable) nBtn.getBackground()).setColor(this.nBtnColor);
            }
            if (this.nBtnVisibe) {
                nBtn.setVisibility(8);
            }
            if (this.negativeBtnText != null) {
                nBtn.setText(this.negativeBtnText);
            }
            iconImg.setImageResource(this.icon);
            if (this.visibility == Icon.Visible) {
                iconImg.setVisibility(0);
            } else {
                iconImg.setVisibility(8);
            }
            if (this.bgColor != 0) {
                view.setBackgroundColor(this.bgColor);
            }
            if (this.pListener != null) {
                pBtn.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.pListener.OnClick();
                        dialog.dismiss();
                    }
                });
            } else {
                pBtn.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
            if (this.nListener != null) {
                nBtn.setVisibility(0);
                nBtn.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.nListener.OnClick();
                        dialog.dismiss();
                    }
                });
            }
            dialog.show();
            return new FancyAlertDialog(this);
        }
    }

    private FancyAlertDialog(Builder builder) {
        this.title = builder.title;
        this.message = builder.message;
        this.activity = builder.activity;
        this.icon = builder.icon;
        this.animation = builder.animation;
        this.visibility = builder.visibility;
        this.pListener = builder.pListener;
        this.nListener = builder.nListener;
        this.positiveBtnText = builder.positiveBtnText;
        this.negativeBtnText = builder.negativeBtnText;
        this.pBtnColor = builder.pBtnColor;
        this.nBtnColor = builder.nBtnColor;
        this.bgColor = builder.bgColor;
        this.cancel = builder.cancel;
        this.nBtnVisibe = builder.nBtnVisibe;
    }
}
