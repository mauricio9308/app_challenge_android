package com.app.appchallenge.fragment.dialog; 

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.app.appchallenge.R;

public class LoadingDialogFragment extends SherlockDialogFragment {
    
    private TextView mTxtMessage; 
    
    private static final String FRAG_ARG_MESSAGE = "frag_arg_message"; 

    public LoadingDialogFragment(){
        /*Default constructor*/
    }
    
    public static LoadingDialogFragment newInstance( String message ){
        Bundle args = new Bundle(); 
        args.putString(FRAG_ARG_MESSAGE, message);
        
        LoadingDialogFragment frag = new LoadingDialogFragment(); 
        frag.setArguments(args); 
        
        return frag; 
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.dialog_fragment_loading /* resource */,
                container /* parent */, false /* attachToRoot */);
    
        mTxtMessage = (TextView) rootView.findViewById(R.id.dialog_txtView_message);
        mTxtMessage.setText( getMessageFromArguments() );
       
        setCancelable( false ); 
        
        return rootView;
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      Dialog dialog = super.onCreateDialog(savedInstanceState);
      
      // request a window without the title
      dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
      return dialog;
    }
 
    public void setMessage(String message){
        mTxtMessage.setText( message ); 
    }
   
    private String getMessageFromArguments(){
        return getArguments().getString(FRAG_ARG_MESSAGE); 
    }
    
    
}
