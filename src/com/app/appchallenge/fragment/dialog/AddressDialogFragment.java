package com.app.appchallenge.fragment.dialog; 

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.app.appchallenge.R;

/**
 * {@link DialogFragment} that request the web service base context
 * 
 * @author Heisenbugs
 * */
public class AddressDialogFragment extends SherlockDialogFragment implements View.OnClickListener {
    
    private  AddressDialogCallback mCallback;
    private EditText mEdtTextAddress; 

    public interface AddressDialogCallback{
    	public void onAddressOk( String address); 
    }
    
    @Override
    public void onAttach( Activity activity){
    	super.onAttach(activity); 
    	
    	try{
			mCallback = ( AddressDialogCallback ) activity;
		} catch ( ClassCastException e ){
			throw new ClassCastException("Class "+ getClass().getSimpleName() + " must implement " + AddressDialogCallback.class.getSimpleName() );
		}
    }
    
    @Override
    public void onDetach(){
    	super.onDetach(); 
    	 
    	mCallback = null; 
    }
    
    public AddressDialogFragment(){
        /*Default constructor*/
    }
    
    public static AddressDialogFragment newInstance(){
        return new AddressDialogFragment();
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.dialog_fragment_ip /* resource */,
                container /* parent */, false /* attachToRoot */);
        rootView.setBackgroundColor(Color.WHITE);
        rootView.findViewById(R.id.bttn_address).setOnClickListener(this);
        
        mEdtTextAddress = ( EditText ) rootView.findViewById(R.id.edtText_address); 
        
        setCancelable( false ); 
        
        return rootView;
    }
    
    @Override
    public void onClick( View view ){
    	mCallback.onAddressOk( mEdtTextAddress.getText().toString() );
    	dismiss(); 
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      Dialog dialog = super.onCreateDialog(savedInstanceState);
      
      // request a window without the title
      dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
      return dialog;
    }
 
}
