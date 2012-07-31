/*
 * Copyright 2012 yamashita@brilliantservice.co.jp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.brilliantservice.android.nfclauncheronhome;

import jp.co.brilliantservice.android.nfchandleronhome.R;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Toast;

/**
 * @author yamashita@brilliantservice.co.jp
 */
public class HomeActivity extends Activity {

    public static final String LOG_TAG = HomeActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Intent intent = getIntent();
        showNfcId(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showNfcId(intent);
    }

    /**
     * IntentがNFCのIntentだった場合、NFC-IDをToastで表示します。
     * 
     * @param intent
     */
    private void showNfcId(Intent intent) {
        if (intent == null)
            return;

        String action = intent.getAction();
        if (TextUtils.isEmpty(action))
            return;

        if (!action.equals(NfcAdapter.ACTION_TECH_DISCOVERED))
            return;

        byte[] rawId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        String id = Util.bytesToString(rawId);

        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
    }

}
