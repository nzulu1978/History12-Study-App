package com.history12.study;
import android.app.*;import android.os.*;import android.graphics.Color;import android.view.*;import android.widget.*;
public class MainActivity extends Activity{
 LinearLayout root; boolean premium; int red=Color.rgb(183,28,28),dark=Color.rgb(35,35,35);
 public void onCreate(Bundle b){super.onCreate(b);premium=getPreferences(0).getBoolean("premium",false);home();}
 TextView tv(String s,int z,int c){TextView v=new TextView(this);v.setText(s);v.setTextSize(z);v.setTextColor(c);v.setPadding(22,16,22,16);return v;}
 Button bt(String s){Button b=new Button(this);b.setText(s);b.setTextSize(16);b.setAllCaps(false);return b;}
 void base(String s){root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(Color.rgb(248,248,248));TextView h=tv("HISTORY 12  •  "+s,19,Color.WHITE);h.setBackgroundColor(red);root.addView(h,new LinearLayout.LayoutParams(-1,70));setContentView(root);}
 void home(){base("GRADE 12 STUDY");root.addView(tv("Grade 12 History Study App",26,dark));root.addView(tv("Learn • Practise • Prepare for the NSC",16,Color.DKGRAY));
 TextView p=tv(premium?"✓ PREMIUM ACTIVE":"★ PREMIUM • R50/month",18,Color.WHITE);p.setBackgroundColor(premium?Color.rgb(46,125,50):dark);root.addView(p);
 String[] free={"Study Topics","Quick Quiz","Flashcards"};for(String s:free){Button b=bt(s);root.addView(b);b.setOnClickListener(v->content(s));}
 String[] lock={"Exemplary Essays 🔒","Picture & Source Library 🔒","Source-Based Practice 🔒","Past-Paper Practice 🔒","Teacher Resources 🔒"};for(String s:lock){Button b=bt(s);root.addView(b);b.setOnClickListener(v->{if(premium)content(s);else paywall();});}
 Button x=bt(premium?"Manage Premium":"Unlock Premium — R50/month");root.addView(x);x.setOnClickListener(v->{if(premium)manage();else paywall();});
 root.addView(tv("Version 3 • Free + Premium access",13,Color.GRAY));}
 void content(String n){base(n.replace(" 🔒",""));root.addView(tv(n.replace(" 🔒",""),24,dark));root.addView(tv("Grade 12 History resources",17,Color.DKGRAY));
 if(n.startsWith("Study")){String[] a={"Cold War","Independent Africa","Civil Society Protests 1950s–1990s","Civil Resistance in South Africa 1970s–1980s","Coming of Democracy","End of Cold War & New Global Order"};for(String t:a){Button b=bt(t);root.addView(b);b.setOnClickListener(v->lesson(t));}}
 else root.addView(tv("This section contains exam-focused study material, questions and revision resources.",17,dark));
 Button back=bt("← Back to Home");root.addView(back);back.setOnClickListener(v->home());}
 void lesson(String t){base("TOPIC");root.addView(tv(t,24,dark));root.addView(tv("Key study points",19,red));root.addView(tv("• Historical context and key events\n• Important people, dates and concepts\n• Cause, consequence, change and continuity\n• Evidence for source-based answers and essays",16,dark));root.addView(tv("Exam tip",19,red));root.addView(tv("Use a clear argument, relevant evidence and a strong conclusion.",16,dark));Button b=bt("← Back");root.addView(b);b.setOnClickListener(v->content("Study Topics"));}
 void paywall(){base("PREMIUM");root.addView(tv("Unlock History 12 Premium",27,dark));root.addView(tv("R50 / month",30,red));root.addView(tv("Premium includes:\n✓ Exemplary essays\n✓ Picture & source library\n✓ Source-based practice\n✓ Past-paper practice\n✓ Advanced quizzes & flashcards\n✓ Teacher resources",17,dark));root.addView(tv("Real payments will be connected through Google Play when the subscription product is configured in Play Console.",14,Color.DKGRAY));
 Button b=bt("Subscribe — R50/month");root.addView(b);b.setOnClickListener(v->testPurchase());Button r=bt("Restore / Check Premium");root.addView(r);r.setOnClickListener(v->testPurchase());Button back=bt("← Back");root.addView(back);back.setOnClickListener(v->home());}
 void testPurchase(){new AlertDialog.Builder(this).setTitle("Premium test").setMessage("The Premium screens are ready. This development build does not charge money yet. Test-unlock Premium now?").setNegativeButton("Cancel",null).setPositiveButton("Test Premium Access",(d,w)->{premium=true;getPreferences(0).edit().putBoolean("premium",true).apply();home();}).show();}
 void manage(){new AlertDialog.Builder(this).setTitle("Premium Active").setMessage("Premium is enabled in this test build. Production subscriptions will be managed through Google Play.").setPositiveButton("OK",null).show();}
}