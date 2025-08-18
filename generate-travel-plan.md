import { NextApiRequest, NextApiResponse } from 'next';

interface TravelPlanRequest {
  destination: string;
  startDate: string;
  endDate: string;
  travelers: string;
  style: string;
}

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse
) {
  if (req.method !== 'POST') {
    return res.status(405).json({ message: 'Method not allowed' });
  }

  const { destination, startDate, endDate, travelers, style } = req.body as TravelPlanRequest;
  const GEMINI_API_KEY = process.env.GEMINI_API_KEY;
  const GEMINI_API_URL = 'https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent';

  if (!GEMINI_API_KEY) {
    console.error('GEMINI_API_KEY is not set');
    return res.status(500).json({ error: 'Server configuration error' });
  }

  try {
    // Calculate number of days
    const start = new Date(startDate);
    const end = new Date(endDate);
    const days = Math.ceil((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)) + 1;

    // Create a prompt for Gemini
    const prompt = `
      Create a detailed ${days}-day travel itinerary for ${travelers} people visiting ${destination} 
      with a focus on ${style} activities. 
      Include specific attractions, restaurants, and estimated times for each activity.
      Format the response as a JSON object with the following structure:
      {
        "itinerary": "Detailed day-by-day plan with timings and activities",
        "recommendations": [
          "Must-visit places",
          "Local food to try",
          "Hidden gems"
        ],
        "tips": [
          "Local customs or etiquette",
          "Transportation tips",
          "Packing suggestions"
        ]
      }
    `;

    const response = await fetch(`${GEMINI_API_URL}?key=${GEMINI_API_KEY}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        contents: [{
          parts: [{
            text: prompt
          }]
        }]
      }),
    });

    if (!response.ok) {
      const error = await response.text();
      console.error('Gemini API error:', error);
      return res.status(response.status).json({ error: 'Failed to generate travel plan' });
    }

    const data = await response.json();
    const generatedText = data.candidates?.[0]?.content?.parts?.[0]?.text || '';
    
    try {
      const parsedResponse = JSON.parse(generatedText);
      return res.status(200).json({
        itinerary: parsedResponse.itinerary || 'No itinerary generated',
        recommendations: parsedResponse.recommendations || [],
        tips: parsedResponse.tips || []
      });
    } catch (e) {
      return res.status(200).json({
        itinerary: generatedText,
        recommendations: [],
        tips: []
      });
    }
  } catch (error) {
    console.error('Error in generate-travel-plan:', error);
    return res.status(500).json({ error: 'Internal server error' });
  }
}
