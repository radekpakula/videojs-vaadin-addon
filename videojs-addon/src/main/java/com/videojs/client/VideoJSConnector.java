package com.videojs.client;


import java.util.ArrayList;
import java.util.List;

import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import com.videojs.VideoJS;


@Connect(VideoJS.class)
public class VideoJSConnector extends AbstractComponentConnector {

	private static final long serialVersionUID = 1L;
	VideoJSServerRpc rpc = RpcProxy.create(VideoJSServerRpc.class, this);

    public VideoJSConnector() {
    }
    @Override
    public VideoJSWidget getWidget() {
        return (VideoJSWidget) super.getWidget();
    }

    @Override
    public VideoJSState getState() {
        return (VideoJSState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        getWidget().setId(getConnectorId());
        getWidget().setSources(getState().sources);
        getWidget().setSubtitles(getState().subtitles);
        getWidget().setTypes(getState().types);
        List<String> urls = new ArrayList<>();
        for(int i=0;i<10;i++){
        	urls.add(getResourceUrl("fileSource"+i));
        }
        getWidget().setUrls(urls);
        List<String> urls2 = new ArrayList<>();
        for(int i=0;i<10;i++){
        	urls2.add(getResourceUrl("subtitles"+i));
        }
        getWidget().setSubtitleUrl(urls2);
        getWidget().rebuildPlayer();
        getWidget().setAttributes(getState().attributes);
    }
    
//	@OnStateChange("page")
//	void showPage() {
//		getWidget().setPage(getState().page);
//	}
}
