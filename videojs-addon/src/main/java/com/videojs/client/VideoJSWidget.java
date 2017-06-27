package com.videojs.client;

import java.util.List;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SourceElement;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.user.client.ui.Label;

// Extend any GWT Widget
public class VideoJSWidget extends Label {
	private List<String> sources;
	private List<String> urls;
	private List<String> subtitles;
	private List<String>  subtitleUrls;
    private String id;
	private List<String> types;
	
	private DivElement root;
	private VideoElement video;
	
	
	public VideoJSWidget() {
        root = Document.get().createDivElement();
        root.setClassName("media-player");
        video=Document.get().createVideoElement();
        video.addClassName("video-js vjs-default-skin");
        video.setAttribute("controls", "");
        video.setAttribute("preload", "none");
        video.setAttribute("width", "640");
        video.setAttribute("height","264");
        video.setAttribute("data-setup","{}");
        root.appendChild(video);
        setElement(root);
		
    }
	public native void log(String log)/*-{
		console.log(log);
	}-*/;
	public void rebuildPlayer() {
		video.setId("videojs-"+id);
		video.removeAllChildren();
		log("reset video");
		log("sources size "+sources.size());
		log("types size "+sources.size());
		log("urls size "+urls.size());
		if(sources!=null && sources.size()>0){
			log("source pass");
			for(int i=0;i<sources.size();i++){
				log("check index "+i);
				String sourceUrl = urls.get(i);
				log("source pass "+i);
				String type = types.get(i);
				log("type pass "+i);
				SourceElement se = Document.get().createSourceElement();
				se.setAttribute("src", sourceUrl);
				log("pass atr src "+i);
				se.setAttribute("type", type);
				log("pass atr type "+i);
				video.appendChild(se);
				log("append child "+i);
			}
		}
		log("check subtitles "+urls.size());
//		if(subtitles!=null && subtitles.size()>0){
////{kind:\"subtitles\",srcLang:\""+srcLang+"\",label:\""+label+"\"}
//			for(int i=0;i<subtitles.size();i++){
//				Element se = Document.get().createElement("track");
//				JSONValue jsonValue = JSONParser.parseStrict(subtitles.get(i));
//				JSONObject jsonObject=null;
//				if ((jsonObject = jsonValue.isObject()) == null) {
//					se.setAttribute("src", subtitleUrls.get(i));
//					se.setAttribute("srclang", jsonObject.get("srcLang").toString());
//					se.setAttribute("label", jsonObject.get("label").toString());
//					se.setAttribute("kind", jsonObject.get("subtitles").toString());
//					video.appendChild(se);
//				}
//			}
//		}
	}
	public void setId(String connectorId) {
		this.id = connectorId;
	}
	public void appendUrls(String url){
		urls.add(url);
	}
	public void setSubtitles(List<String> subtitles) {
		this.subtitles=subtitles;
	}
	public void setSources(List<String> sources) {
		this.sources=sources;
	}
	public void setTypes(List<String> types) {
		this.types=types;
	}
	public void setUrls(List<String> urls) {
		this.urls=urls;
	}
	public void setSubtitleUrl(List<String> subtitleUrls) {
		this.subtitleUrls=subtitleUrls;
	}
}