package com.example.mimowidget;
import android.appwidget.*;import android.content.*;import android.widget.RemoteViews;
public class Widget extends AppWidgetProvider {
 static final String REFRESH="com.mimo.tokenwidget.REFRESH";
 @Override public void onUpdate(Context c,AppWidgetManager m,int[] ids){Data.schedule(c);Data.update(c);new Thread(()->Data.refresh(c.getApplicationContext())).start();}
 @Override public void onReceive(Context c,Intent intent){super.onReceive(c,intent);if(REFRESH.equals(intent.getAction())){Context app=c.getApplicationContext();RemoteViews views=new RemoteViews(c.getPackageName(),com.mimo.tokenwidget.R.layout.widget);views.setTextViewText(com.mimo.tokenwidget.R.id.status,"Обновление…");AppWidgetManager manager=AppWidgetManager.getInstance(c);manager.partiallyUpdateAppWidget(manager.getAppWidgetIds(new ComponentName(c,Widget.class)),views);PendingResult pending=goAsync();new Thread(()->{try{Data.refresh(app);}finally{pending.finish();}}).start();}}
}
