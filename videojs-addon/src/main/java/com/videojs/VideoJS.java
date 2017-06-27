package com.videojs;

import java.io.File;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.FileResource;
import com.videojs.client.VideoJSServerRpc;
import com.videojs.client.VideoJSState;

@JavaScript({"video.js"})
public class VideoJS extends com.vaadin.ui.AbstractComponent {
	private static final long serialVersionUID = 1L;
	private VideoJSServerRpc rpc = new VideoJSServerRpc(){
		private static final long serialVersionUID = 1L;
		
	};
    public VideoJS() {
        registerRpc(rpc);
    }

    @Override
    protected VideoJSState getState() {
        return (VideoJSState) super.getState();
    }
	public void appendSource(File source, String type){
		int counter = getState().sources.size();
		getState().sources.add(source.getName());
		getState().types.add(type);
		setResource("fileSource"+counter, new FileResource(source));
	}
	public void appendSubtitles(File source, String srcLang, String label){
		int counter = getState().subtitles.size();
		setResource("subtitles"+counter, new FileResource(source));
		getState().subtitles.add("{kind:\"subtitles\",srcLang:\""+srcLang+"\",label:\""+label+"\"}");
	}
}
